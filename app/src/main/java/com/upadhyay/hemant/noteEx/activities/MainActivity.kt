package com.upadhyay.hemant.noteEx.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.upadhyay.hemant.noteEx.R
import com.upadhyay.hemant.noteEx.activities.home.HomeFragment
import com.upadhyay.hemant.noteEx.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Log.d("MainActivity","Replace to Home Fragment" + HomeFragment.newInstance().toString())
        replaceFragment(HomeFragment.newInstance(), false)

    }

    fun replaceFragment(fragment: Fragment, istransition: Boolean) {

        val fragmentTransition = supportFragmentManager.beginTransaction()

        if (istransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransition.replace(R.id.frameLayoutFragment, fragment).addToBackStack(null)
        fragmentTransition.commit()
        Log.d("TAG","fragment commited")
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
        Log.d("TAG","Mainactivity backbutton pressed")
        finish()
    }

}
