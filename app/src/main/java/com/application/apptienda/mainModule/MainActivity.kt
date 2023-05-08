package com.application.apptienda.mainModule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.application.apptienda.R
import com.application.apptienda.common.utils.Constants
import com.application.apptienda.databinding.ActivityMainBinding
import com.application.apptienda.mainModule.viewModel.MainViewModel
import com.application.apptienda.ui.buy.BuyFragment
import com.application.apptienda.ui.buy.PaymentResultListener
import com.application.apptienda.mainModule.viewModel.ProductViewModel
import com.application.apptienda.ui.buy.DialogBuy
import com.application.apptienda.ui.buy.DialogListener
import com.mercadopago.android.px.core.MercadoPagoCheckout
import com.mercadopago.android.px.model.Payment
import com.mercadopago.android.px.model.exceptions.MercadoPagoError

class MainActivity : AppCompatActivity(), PaymentResultListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferencia: String

    //MVVM
    lateinit var mMainViewModel: MainViewModel
    lateinit var productViewModel: ProductViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        setUpViewModel()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    private fun setUpViewModel() {
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mMainViewModel.getPaymentMethod().observe(this, {paymentMethod ->
            Log.i("Caro: ", paymentMethod.toString())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    // En el método donde inicias el pago, asignar el fragmento como listener
    fun startPayment() {
        val fragment = BuyFragment()
        fragment.setPaymentResultListener(this)
        // ...
    }

    // Implementar el método onActivityResult en la actividad
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("onActivityResult ::::: ", requestCode.toString())
        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE) {
                Log.i("MercadoPagoCheckout.PAYMENT_RESULT_CODE ::::: ", resultCode.toString())
                // El pago se completó exitosamente
                val payment = data?.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT) as? Payment
                if (payment != null) {
                    // Pasar los resultados al fragmento
                    onPaymentSuccess(payment)
                }
            } else if (resultCode == RESULT_CANCELED) {
                // El usuario canceló el pago
                onPaymentCancelled()
            } else if (resultCode == MercadoPagoCheckout.SESSION_EXPIRED_RESULT_CODE) {
                // Ocurrió un error al procesar el pago
                val mercadoPagoError = data?.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR) as? MercadoPagoError
                if (mercadoPagoError != null) {
                    // Pasar los resultados al fragmento
                    onPaymentError(mercadoPagoError)
                }
            }
        }
    }

    // Implementar los métodos de la interfaz en la actividad
    override fun onPaymentSuccess(payment: Payment) {
        // ...
    }

    override fun onPaymentError(mercadoPagoError: MercadoPagoError) {
        // ...
    }

    override fun onPaymentCancelled() {
        // ...
    }

}