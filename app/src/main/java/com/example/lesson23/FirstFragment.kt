package com.example.lesson23

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson23.databinding.FragmentFirstBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.timerTask

class FirstFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding?.btnStartCoroutine?.setOnClickListener {


//            Log.e("Coroutines", "i equals: ")
//            CoroutineScope(Dispatchers.IO).launch {
//
//            while (true) {
//                //delay(1000)
////                Handler(Looper.getMainLooper()).postDelayed({
////
////                }, 1000)
//
//                Log.e("Coroutines", "i equals: ")
//            }
//        }

            val deferredSomeResult = CoroutineScope(Dispatchers.IO).async {

                getSomeResult()
            }

            CoroutineScope(Dispatchers.Main).launch {

                Log.e("Coroutines", "The text is: ${deferredSomeResult.await()}")
                binding?.btnStartCoroutine?.text = deferredSomeResult.await()
            }
        }
    }

    // Нужно сделать две кнопки и TextView. Одна кнопка должна быть с текстом changeText.
    // Вторая кнопка должна быть с текстом clear text. Нужно сделать list с String значениями.
    // По нажатию на кнопку с текстом changeText должна происходить пауза на 2500 миллисекунд.
    // И затем должен меняться TextView на полученный текст из листа через функцию random.
    // Для замены текста нужно использовать корутину с async.
    // Для очистки текста нужно использовать корутину с launch.

    private fun getSomeResult(): String {

        Thread.sleep(3000)

        return "Hey, Kotlin!"
    }
}