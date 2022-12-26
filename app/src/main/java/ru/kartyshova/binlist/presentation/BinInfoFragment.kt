package ru.kartyshova.binlist.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kartyshova.binlist.data.Bin
import ru.kartyshova.binlist.databinding.FragmentBinInfoBinding


class BinInfoFragment : Fragment() {

    private var binding: FragmentBinInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinInfoBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binBin = arguments?.getParcelable<Bin>("arg") ?: return

        binding?.run {
            length.text = binBin.number?.length.toString()
            luhn.text = binBin.number?.luhn.toString()
            scheme.text = binBin.scheme
            type.text = binBin.type
            brand.text = binBin.brand
            prepaid.text = binBin.prepaid.toString()
            alpha2.text = binBin.country?.alpha2
            name.text = binBin.country?.name
            latitude.text = binBin.country?.latitude.toString()
            longitude.text = binBin.country?.longitude.toString()
            nameBTitle.text = binBin.bank?.name
            city.text = binBin.bank?.city
            url.text = binBin.bank?.url
            phone.text = binBin.bank?.phone

        }

        binding?.url?.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("http://" + binBin.bank?.url)
            startActivity(openUrl)
        }

        binding?.phone?.setOnClickListener {
            val callPhone = Intent(Intent.ACTION_DIAL)
            callPhone.data = Uri.parse("tel:" + binBin.bank?.phone)
            startActivity(callPhone)
        }

        binding?.country?.setOnClickListener {
            val openCart = Intent(Intent.ACTION_VIEW)
            val cart = "geo:"+ binBin.country?.latitude + "," + binBin.country?.longitude
            openCart.data = Uri.parse(cart)
            startActivity(openCart)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}