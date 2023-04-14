package com.example.blinkitclone.Utils

import android.graphics.Color
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import android.widget.Toast


fun makeTextViewResizable(
    tv: TextView,
    maxLine: Int,
    expandText: String,
    viewMore: Boolean,
    fullText: String
) {
    if (tv.tag == null) {
        tv.tag = tv.text
    }
    val vto = tv.viewTreeObserver
    vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            val obs = tv.viewTreeObserver
            obs.removeGlobalOnLayoutListener(this)
            if (maxLine == 0) {
                val lineEndIndex = tv.layout.getLineEnd(0)
                val text = tv.text.subSequence(0, lineEndIndex - expandText.length + 1)
                    .toString() + " " + expandText
                tv.text = text
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        Html.fromHtml(tv.text.toString()), tv, maxLine, expandText,
                        viewMore,
                        fullText
                    ), TextView.BufferType.SPANNABLE
                )
            } else if (maxLine > 0 && tv.lineCount >= maxLine) {
                val lineEndIndex = tv.layout.getLineEnd(maxLine - 1)
                val text = tv.text.subSequence(0, lineEndIndex - expandText.length + 1)
                    .toString() + " " + expandText
                tv.text = text
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        Html.fromHtml(tv.text.toString()), tv, maxLine, expandText,
                        viewMore,
                        fullText
                    ), TextView.BufferType.SPANNABLE
                )
            } else {
                val lineEndIndex = tv.layout.getLineEnd(tv.layout.lineCount - 1)
                val text = tv.text.subSequence(0, lineEndIndex).toString() + " " + expandText
                tv.text = text
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        Html.fromHtml(tv.text.toString()), tv, lineEndIndex, expandText,
                        viewMore,
                        fullText
                    ), TextView.BufferType.SPANNABLE
                )
            }
        }
    })
}

private fun addClickablePartTextViewResizable(
    strSpanned: Spanned, tv: TextView,
    maxLine: Int, spanableText: String, viewMore: Boolean, fullText: String
): SpannableStringBuilder? {
    val str = strSpanned.toString()
    val ssb = SpannableStringBuilder(strSpanned)
    if (str.contains(spanableText)) {
        ssb.setSpan(object : MySpannable(false, tv,spanableText,maxLine,viewMore,fullText) {

        }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length, 0)
    }
    return ssb
}

open class MySpannable(
    isUnderline: Boolean,
    val tv: TextView,
    var expandText: String,
    val maxLine: Int,
    val viewMore: Boolean,
    val fullText: String
) : ClickableSpan() {
    private var isUnderline = true

    /**
     * Constructor
     */
    init {
        this.isUnderline = isUnderline
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = isUnderline
        ds.color = Color.parseColor("#07A915")
    }

    override fun onClick(widget: View) {

        if (expandText.equals("view more details"))
        {
            expandText = "view less details"
            val text = fullText + " " + expandText

            //tv.setText(Html.fromHtml(text+"<font color='red'> <u>$expandText</u></font>"))

            tv.setText(
               addClickablePartTextViewResizable(
                   Html.fromHtml(text), tv, maxLine, expandText,
                   viewMore,fullText
               ), TextView.BufferType.SPANNABLE
           )


        }
        else if (expandText.equals("view less details")) {

            expandText = "view more details"

            if (maxLine == 0) {
                val lineEndIndex = tv.layout.getLineEnd(0)
                val text = tv.text.subSequence(0, lineEndIndex - expandText.length + 1)
                    .toString() + " " + expandText
                tv.text = text
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        Html.fromHtml(tv.text.toString()), tv, maxLine, expandText,
                        viewMore,
                        fullText
                    ), TextView.BufferType.SPANNABLE
                )
            } else if (maxLine > 0 && tv.lineCount >= maxLine) {
                val lineEndIndex = tv.layout.getLineEnd(maxLine - 1)
                val text = tv.text.subSequence(0, lineEndIndex - expandText.length + 1)
                    .toString() + " " + expandText
                tv.text = text
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        Html.fromHtml(tv.text.toString()), tv, maxLine, expandText,
                        viewMore,
                        fullText
                    ), TextView.BufferType.SPANNABLE
                )
            } else {
                val lineEndIndex = tv.layout.getLineEnd(tv.layout.lineCount - 1)
                val text = tv.text.subSequence(0, lineEndIndex).toString() + " " + expandText
                tv.text = text
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        Html.fromHtml(tv.text.toString()), tv, lineEndIndex, expandText,
                        viewMore,
                        fullText
                    ), TextView.BufferType.SPANNABLE
                )
            }

        }

    }

}