package com.example.temp.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.temp.R
import com.example.temp.databinding.ActivityTodoBinding
import com.example.temp.todo.models.response.ContryEntryInfoResponse
import com.example.temp.todo.network.TodoActivityInterface
import com.example.temp.todo.network.TodoService
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_todo.*

class TodoActivity : AppCompatActivity(),TodoActivityInterface {
    private var TAG="TodoActivityTAG"
    lateinit var todoDocFragment: TodoDocFragment
    lateinit var todoPackFragment: TodoPackFragment

    lateinit var binding: ActivityTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        todoDocFragment=TodoDocFragment()
        todoPackFragment=TodoPackFragment()

        supportFragmentManager.beginTransaction().add(R.id.frameLayout,todoPackFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.frameLayout,todoDocFragment).commit()

        replaceView(todoDocFragment)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        replaceView(todoDocFragment)
                    }
                    1 -> {
                        replaceView(todoPackFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })



//        TodoService(this@TodoActivity).tryGetContryEntryInfo("ZHjMzIWX5bq/z7LHhg8Aa9pQ+zTXdLoU71TjpZl6pcECtIPV9hPEu2xQTbcBHWeTxff7YpY72QPyISTtW7m51w==",
//        1,10,"JSON","일본")

    }
    //화면변경
    private fun replaceView(tab:Fragment){

        if(tab is TodoDocFragment){
            supportFragmentManager.beginTransaction().hide(todoPackFragment!!).commit()
            supportFragmentManager.beginTransaction().show(todoDocFragment!!).commit()
        }

        if(tab is TodoPackFragment){
            supportFragmentManager.beginTransaction().hide(todoDocFragment!!).commit()
            supportFragmentManager.beginTransaction().show(todoPackFragment!!).commit()

        }

//        tab.let{
//            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, it).commit()
//        }
    }

    override fun onContryEntryInfoSuccess(response: ContryEntryInfoResponse) {
        Log.d(TAG,response.data[0].txt_origin_cn)
    }

    override fun onContryEntryInfoFailure(message: String, code: Int) {
        Log.d(TAG,"[onContryEntryInfoFailure] : $code , $message")

    }
}