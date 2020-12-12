package com.example.simulacion02dlassallec

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simulacion02dlassallec.model.local.Housy
import com.example.simulacion02dlassallec.viewmodel.HousyViewModel

class FirstFragment : Fragment(), HousyAdapter.PassHouseData {

    private val myViewModel: HousyViewModel by activityViewModels()
    lateinit var myAdapter: HousyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdapter = HousyAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerList)
        myRecyclerView.layoutManager = LinearLayoutManager(context)
        myRecyclerView.adapter = myAdapter


        myViewModel.allHouses.observe(viewLifecycleOwner, Observer {
            Log.d("casas", it.toString())

            myAdapter.updateAdapter(it)
        })

    }

    override fun passHouseDetails(house: Housy) {
        val houseSelectedId = house.id
        myViewModel.houseSelected(houseSelectedId)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}