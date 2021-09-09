package uz.ipanda.ussd

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.runtimepermission.kotlin.askPermission
import uz.ipanda.ussd.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPermission()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        binding.navView.itemIconTintList = null
        binding.navView.itemTextColor = null

//         supportActionBar?.setDisplayShowTitleEnabled(false)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_Fragment, R.id.operator
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.telegram -> {
                    shareTelegram()
                }
                R.id.about_us -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Biz haqimizda")
                    builder.setMessage("Ushbu dastur PDP IT Academiyasi bitiruvchisi Akramov Farhod tomonidan yaratilgan")
                    builder.setPositiveButton("OK") { dialog, which ->

                    }
                    builder.show()
                }
                R.id.share -> {
                    share()
                }
                R.id.rate -> {
                    val uri =
                        Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.katana&hl=ru&gl=US" + this.packageName)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(intent)
                    } catch (e: Exception) {
                        binding.drawerLayout.close()
                        Snackbar.make(
                            binding.root,
                            "Qayta urining !",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }

            true
        }

        binding.appBarMain.include.balance.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.setData(Uri.parse("tel:" + "*100" + Uri.encode("#")))
            startActivity(intent)
        }
        binding.appBarMain.include.operator.setOnClickListener {
            navController.navigate(R.id.operatorFragment)
        }
        binding.appBarMain.include.homeBtn.setOnClickListener {
            if (navController.currentDestination!!.id != R.id.home_Fragment) {
              navController.navigate(R.id.home_Fragment)
            }
        }
        binding.appBarMain.include.news.setOnClickListener {
            if (navController.currentDestination!!.id != R.id.newsFragment) {
                navController.navigate(R.id.newsFragment)
            }

        }
        binding.appBarMain.include.settings.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)

            startActivity(intent)
        }
    }

    private fun shareTelegram() {
        val uri = Uri.parse("https://t.me/Akramov_Farhod" + this.packageName)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(intent)
        } catch (e: Exception) {
            binding.drawerLayout.close()
            Snackbar.make(
                binding.root,
                "Qayta urining !",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun share() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
            var shareMessage = "\nLet me recommend you this application\n\n"
            shareMessage =
                """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                            
                            
                            """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: java.lang.Exception) {
            //e.toString();
        }
    }


    private fun getPermission() {
        askPermission(android.Manifest.permission.CALL_PHONE) {

        }
            .onDeclined {


                if (it.hasDenied()) {
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setMessage("Ushbu ilovadan foydalanish uchun quyidagi so\'rovga ruxsat berishingiz kerak!")
                    alertDialog.setPositiveButton(
                        "OK",
                        DialogInterface.OnClickListener { dialog, which ->
                            it.askAgain()
                        })
                    alertDialog.show()
                }

                if (it.hasForeverDenied()) {
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setMessage("Ushbu ilovadan foydalanish uchun sozlamalardagi imkoniyatlarni yoqishingiz yoki ilovani qaytadan o\'rnatib olishingiz kerak!")
                    alertDialog.setPositiveButton(
                        "OK",
                        DialogInterface.OnClickListener { dialog, which ->
                            it.goToSettings()
                        })

                    alertDialog.show()
                }

            }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.telegram -> {
                shareTelegram()
            }
            R.id.share -> share()

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }

}