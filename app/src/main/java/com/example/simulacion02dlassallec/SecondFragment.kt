package com.example.simulacion02dlassallec

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.simulacion02dlassallec.model.local.Housy
import com.example.simulacion02dlassallec.viewmodel.HousyViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception


class SecondFragment : Fragment() {

    private val myViewModel: HousyViewModel by activityViewModels()
    private var idHouse = 0
    lateinit var houseName: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.houseSelection.observe(viewLifecycleOwner, Observer {
            idHouse = it

            myViewModel.getOneHouse(idHouse).observe(viewLifecycleOwner, Observer {
                it?.let {

                    houseName = it.name

                    val imgHouse = view.findViewById<ImageView>(R.id.imgSelected)
                    Glide.with(this).load(it.photo).into(imgHouse)
                    val nameHouse = view.findViewById<TextView>(R.id.nameSelected)
                    val priceHouse = view.findViewById<TextView>(R.id.priceSel)
                    val sizeHouse = view.findViewById<TextView>(R.id.sizeSel)
                    val renovHouse = view.findViewById<TextView>(R.id.renovacionSel)
                    val creditHouse = view.findViewById<TextView>(R.id.creditoSel)
                    val descHouse = view.findViewById<TextView>(R.id.tvdescription)



                    nameHouse.text = it.name
                    priceHouse.text = it.price.toString()
                    sizeHouse.text = it.size.toString()
                    descHouse.text =
                        it.description + getString(R.string.motivo_venta) + " " + it.cause

                    if (it.renovation == true) {
                        renovHouse.text = getString(R.string.renovation_true)
                    } else {
                        renovHouse.text = getString(R.string.renovation_false)
                    }

                    if (it.credit == true) {
                        creditHouse.text = getString(R.string.credit_true)
                    } else {
                        creditHouse.text = getString(R.string.credit_false)
                    }


                }
            })
        })


        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val mailAddress = getString(R.string.mail_address)
            val mailSubject = getString(R.string.mail_subject) + " " + houseName + " Id $idHouse"
            val mailText =
                getString(R.string.mail_text1) + " " + houseName + " " + getString(R.string.mail_text2)
            createEmail(mailAddress, mailSubject, mailText)
        }



        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    fun createEmail(mailAddress: String, mailSubject: String, mailText: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mailAddress))
        intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject)
        intent.putExtra(Intent.EXTRA_TEXT, mailText)

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }

    }
}