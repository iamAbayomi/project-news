package com.oladiniabayomi.digitalnews.onboarding

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.adjust.sdk.Adjust
import com.codemybrainsout.onboarder.AhoyOnboarderActivity
import com.codemybrainsout.onboarder.AhoyOnboarderCard
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import com.facebook.applinks.AppLinks
import com.oladiniabayomi.digitalnews.AdsActivity
import com.oladiniabayomi.digitalnews.R
import com.oladiniabayomi.digitalnews.helpers.SignInHelper
import com.oladiniabayomi.digitalnews.helpers.WalkThroughHelper
import com.oladiniabayomi.digitalnews.network.AdsWebService
import com.oladiniabayomi.digitalnews.views.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class OnboardingActivity : AhoyOnboarderActivity() {

    private var iconHeight: Int = 185
    private var iconWidth: Int = 185

    private var marginTop: Int = 4
    private var marginLeft: Int = 4
    private var marginRight: Int = 4
    private var  marginBottom: Int = 4

    private var signInHelper: SignInHelper? = null
    private var preferenceHelper: WalkThroughHelper? = null

    var GAID: String?= ""
    var bannerID : String? = ""
    var anotherBannerId: String? = " "
    var parentJob = Job()
    var coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)
    var fileExtension : String? = ""
    var result : String? = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAdjustID()

        try {
            val info = packageManager.getPackageInfo(
                "your.package",
                PackageManager.GET_SIGNATURES
            )

          //  Toast.makeText(this, "Here", Toast.LENGTH_LONG).show()

            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))

                Toast.makeText(applicationContext,"This is the result $"+ Base64.encodeToString(md.digest(), Base64.DEFAULT), Toast.LENGTH_LONG).show()
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }

        val ahoyOnboarderCard1 = AhoyOnboarderCard("View Articles", "Get update with written posts from various writers around the globe", R.drawable.tell)
        ahoyOnboarderCard1.setBackgroundColor(R.color.white)
        ahoyOnboarderCard1.setTitleColor(R.color.black)
        ahoyOnboarderCard1.setDescriptionColor(R.color.grey_600)
        ahoyOnboarderCard1.setTitleTextSize(dpToPixels(10, this))
        ahoyOnboarderCard1.setDescriptionTextSize(dpToPixels(8, this))

        val ahoyOnboarderCard2 = AhoyOnboarderCard("Save Articles", "View info even when there is no internet connection", R.drawable.tell)
        ahoyOnboarderCard2.setBackgroundColor(R.color.white)
        ahoyOnboarderCard2.setTitleColor(R.color.black)
        ahoyOnboarderCard2.setDescriptionColor(R.color.grey_600)
        ahoyOnboarderCard2.setTitleTextSize(dpToPixels(10, this))
        ahoyOnboarderCard2.setDescriptionTextSize(dpToPixels(8, this))

        val ahoyOnboarderCard3 = AhoyOnboarderCard("Be Informed", "Get information Daily ", R.drawable.tell)
        ahoyOnboarderCard3.setBackgroundColor(R.color.white)
        ahoyOnboarderCard3.setTitleColor(R.color.black)
        ahoyOnboarderCard3.setDescriptionColor(R.color.grey_600)
        ahoyOnboarderCard3.setTitleTextSize(dpToPixels(10, this))
        ahoyOnboarderCard3.setDescriptionTextSize(dpToPixels(8, this))

        var pages  =  ArrayList<AhoyOnboarderCard>()
        pages.add(ahoyOnboarderCard1)
        pages.add(ahoyOnboarderCard2)
        pages.add(ahoyOnboarderCard3)

        var colorList  =  ArrayList<Int>()
        colorList.add(R.color.solid_one)
        colorList.add(R.color.solid_two);
        colorList.add(R.color.solid_three);

        setColorBackground(colorList)
        setOnboardPages(pages)
        setFinishButtonTitle("Get Started")

        preferenceHelper = WalkThroughHelper(this)
        if(preferenceHelper!!.intro.equals("no")){
            val intent  = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onFinishButtonPressed() {
        val intent  = Intent(this, MainActivity::class.java)
        startActivity(intent)
        getAdjustID()
        preferenceHelper!!.putIntro("no")
    }

    fun getAdjustID(){
        Adjust.getGoogleAdId(this) {
          //  Toast.makeText(applicationContext, "This is the ads ID : $it", Toast.LENGTH_LONG).show()
            GAID = it
            Log.d("Adjust:advertisingID","This is the ads ID : $it")
        }
       // Toast.makeText(this, "This is the ads ID : $GAID", Toast.LENGTH_LONG).show()

        getBannerId()

    }

    fun getValuefromWebpage(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://freestyleapps.xyz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AdsWebService::class.java)
        val call = service.getValue()

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if(response!!.code()==200)
                {
                    result = response.body()

                    if(result == "1"){
                        val intent = Intent(this@OnboardingActivity, AdsActivity::class.java)
                        intent.putExtra("GAID", GAID)
                        intent.putExtra("BANNER_ID", fileExtension)
                        startActivity(intent)
                       // Toast.makeText(applicationContext,"This is the result $result", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                   // Toast.makeText(applicationContext,"Ko wo 200 O!!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
               // Toast.makeText(applicationContext,"Error ${t.toString()}", Toast.LENGTH_LONG).show()
            }
        })
    }


    fun getBannerId(){
        FacebookSdk.setAutoInitEnabled(true)
        FacebookSdk.setAutoLogAppEventsEnabled(true)
        FacebookSdk.setAdvertiserIDCollectionEnabled(true)

        FacebookSdk.fullyInitialize()
        var target = bolts.AppLinks.getTargetUrl(intent)
      //  Toast.makeText(this, target?.toString(), Toast.LENGTH_LONG).show()

        AppLinkData.fetchDeferredAppLinkData(applicationContext
        ) {
            bannerID = it?.targetUri.toString()
            val regex = Regex("[^app://]")
            //var filepath = "app://test111"
            // println("File extension -> $fileExtension")
            var thread =  Thread {
                runOnUiThread {
                    fileExtension = bannerID?.substringAfterLast(delimiter = "/", missingDelimiterValue = "")

                    //Toast.makeText(this, "This is the BannerId : $bannerID + regexID " +
                      //      "$fileExtension", Toast.LENGTH_LONG).show()
                    getValuefromWebpage()
                } }
                thread.start()

            Log.d("Banner","This is the BannerId : $bannerID")
            Log.d("Banner","This is the regexId : $fileExtension")
        }

    }

    fun getbannerId(bannerID: String){
        //Toast.makeText(applicationContext, "This is the banner ID : $bannerID", Toast.LENGTH_LONG).show()
    }


}
