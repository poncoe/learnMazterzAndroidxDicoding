package id.poncoe.latihanmasterandroidponcoe.kotlin.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import id.poncoe.latihanmasterandroidponcoe.R

class MyEditText : AppCompatEditText {

    internal lateinit var mClearButtonImage: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Menambahkan hint pada editText
        hint = "Masukkan nama Anda"

        // Menambahkan text aligmnet pada editText
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        // Menginisialisasi gambar clear button
        mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_black_24dp, null) as Drawable

        // Menambahkan aksi kepada clear button
        setOnTouchListener(OnTouchListener { v, event ->
            if (compoundDrawablesRelative[2] != null) {
                val clearButtonStart: Float
                val clearButtonEnd: Float
                var isClearButtonClicked = false
                when (layoutDirection) {
                    View.LAYOUT_DIRECTION_RTL -> {
                        clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                        when {
                            event.x < clearButtonEnd -> isClearButtonClicked = true
                        }
                    }
                    else -> {
                        clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                        when {
                            event.x > clearButtonStart -> isClearButtonClicked = true
                        }
                    }
                }
                when {
                    isClearButtonClicked -> when {
                        event.action == MotionEvent.ACTION_DOWN -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_black_24dp, null) as Drawable
                            showClearButton()
                            return@OnTouchListener true
                        }
                        event.action == MotionEvent.ACTION_UP -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_black_24dp, null) as Drawable
                            when {
                                text != null -> text?.clear()
                            }
                            hideClearButton()
                            return@OnTouchListener true
                        }
                        else -> return@OnTouchListener false
                    }
                    else -> return@OnTouchListener false
                }
            }
            false
        })

        // Menambahkan aksi ketika ada perubahan text akan memunculkan clear button
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                when {
                    !s.toString().isEmpty() -> showClearButton()
                }
            }

            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }

    // Menampilkan clear button
    private fun showClearButton() {
        // Sets the Drawables (if any) to appear to the left of,
        // above, to the right of, and below the text.
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, // Top of text.
            mClearButtonImage, null)// Start of text.
        // End of text.
        // Below text.
    }

    // Menghilangkan clear button
    private fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)// Start of text.
        // Top of text.
        // End of text.
        // Below text.
    }
}