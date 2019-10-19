package com.androindian.regkotlin

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var url="http://androindian.com/apps/example_app/api.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Reg.setOnClickListener {
            var jsonObject=JSONObject()
            jsonObject.put("name",Name.text.toString().trim())
            jsonObject.put("mobile",Mobile.text.toString().trim())
            jsonObject.put("email",email.text.toString().trim())
            jsonObject.put("pswrd",pass.text.toString().trim())
            jsonObject.put("baction","register_user")

            var reguser= RegUser()
            reguser.execute(jsonObject.toString())

        }
    }


inner class RegUser : AsyncTask<String, String, String>() {
    var progressDialog = ProgressDialog(this@MainActivity)

    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog.setMessage("Please Wait")
        progressDialog.setTitle("Content Loading")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    override fun doInBackground(vararg params: String?): String {
        var j2: JSONObject? = JsonFunctions.RequestPost(url, params[0]);
        return j2.toString()

    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        progressDialog.dismiss()

        val j3 = JSONObject(result!!.toString())

        val r1 = j3.getString("response")

        if (r1.equals("failed")) {
            val r2 = j3.getString("user")
            Toast.makeText(this@MainActivity, "" + r2, Toast.LENGTH_SHORT).show()


        } else if (r1.equals("success")) {
            val r2 = j3.getString("user")
            Toast.makeText(this@MainActivity, "" + r2, Toast.LENGTH_SHORT).show()

        } else {
            val r2 = j3.getString("user")
            Toast.makeText(this@MainActivity, "" + r2, Toast.LENGTH_SHORT).show()

        }
    }
}

        /*var j3 = JSONObject(result!!.toString())

        var res=JSONObject("response")

        if(res.equals("failed")){
            var  res2=j3.getJSONObject("user")
            Toast.makeText(this@MainActivity,""+res2,Toast.LENGTH_LONG).show()

        }else if(res.equals("success")){
            var  res2=j3.getJSONObject("user")
            Toast.makeText(this@MainActivity,""+res2,Toast.LENGTH_LONG).show()


        }else{
            var  res2=j3.getJSONObject("user")
            Toast.makeText(this@MainActivity,""+res2,Toast.LENGTH_LONG).show()


        }
    }*/


}
