package com.google.firebase.quickstart.auth.abxmlparsing

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var txtview: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtview = findViewById<TextView>(R.id.textView)
        try {
            val inputStream = assets.open("example.xml")
            val dbFactory = DocumentBuilderFactory.newInstance()
            val dBuilder = dbFactory.newDocumentBuilder()
            val doc = dBuilder.parse(inputStream)
            val element = doc.documentElement
            element.normalize()
            val nList = doc.getElementsByTagName("student")
            for (i in 0 until nList.length) {
                val node = nList.item(i)
                if (node.nodeType === Node.ELEMENT_NODE) {
                    val element2 = node as Element
                    txtview.text = "" + txtview.text + "\nName : " + getValue("name", element2) + "\n"
                    txtview.text = "" + txtview.text + "Surname : " + getValue("surname", element2) + "\n"
                    txtview.text = "" + txtview.text + "Section : " + getValue("section", element2) + "\n"
                    txtview.text = "" + txtview.text + "-----------------------"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getValue(tag: String, element: Element): String {
        val nodeList = element.getElementsByTagName(tag).item(0).childNodes
        val node = nodeList.item(0)
        return node.nodeValue
    }
}