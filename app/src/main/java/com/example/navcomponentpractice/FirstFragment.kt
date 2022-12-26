package com.example.navcomponentpractice

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.navcomponentpractice.FirstFragmentDirections
import com.example.navcomponentpractice.databinding.FragmentFirstBinding
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment() : Fragment(),TempInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFirstBinding
    lateinit var tempInterface: TempInterface

    constructor(parcel: Parcel) : this() {
        param1 = parcel.readString()
        param2 = parcel.readString()
        tempInterface = parcel.readParcelable(TempInterface::class.java.classLoader)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFirstBinding.inflate(layoutInflater)
        tempInterface=this
        val temp="Dummy Data"
        val testModel=TempModel("It's String",tempInterface)

        binding.txtOne.setOnClickListener {
            Log.d("waqar","checking")
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(temp,tempInterface,testModel))

//            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment("temp",tempInterface))
        }

        //By BackStack Method...........
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("MyData")?.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),"Message was $it",Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    /*companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/

    override fun mybackpress(data:String) {
        Toast.makeText(requireContext(),data,Toast.LENGTH_SHORT).show()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirstFragment> {
        override fun createFromParcel(parcel: Parcel): FirstFragment {
            return FirstFragment(parcel)
        }

        override fun newArray(size: Int): Array<FirstFragment?> {
            return arrayOfNulls(size)
        }
    }
}