package com.oladiniabayomi.digitalnews.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.adjust.sdk.Adjust
import com.codemybrainsout.onboarder.AhoyOnboarderActivity
import com.codemybrainsout.onboarder.AhoyOnboarderCard
import com.oladiniabayomi.digitalnews.R
import com.oladiniabayomi.digitalnews.helpers.SignInHelper
import com.oladiniabayomi.digitalnews.helpers.WalkThroughHelper
import com.oladiniabayomi.digitalnews.views.MainActivity


class OnboardingActivity : AhoyOnboarderActivity() {

    private var iconHeight: Int = 185
    private var iconWidth: Int = 185

    private var marginTop: Int = 4
    private var marginLeft: Int = 4
    private var marginRight: Int = 4
    private var  marginBottom: Int = 4

    private var signInHelper: SignInHelper? = null
    private var preferenceHelper: WalkThroughHelper? = null

    var GAID= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
        preferenceHelper!!.putIntro("no")
    }

    fun getAdjustID(){
        Adjust.getGoogleAdId(this) {
            Toast.makeText(applicationContext, "This is the ads ID : $it", Toast.LENGTH_LONG).show()
            GAID = it
            Log.d("Adjust:advertisingID","This is the ads ID : $it")
        }
        //getValuefromWebpage()
    }

}
