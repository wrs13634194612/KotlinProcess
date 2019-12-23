package com.ufi.pdioms.ztkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View.OnLongClickListener

class MainActivity : AppCompatActivity() {
    private var SYNC_INCSUB = 0
    private var BOOL_HP = false
    private var HP_Freq = 0
    private var LP_Freq = 0
    private var Freq = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        id_mcl_seekbar_valume.setOnSeekBarChangeListener { mhs_SeekBar, progress, fromUser ->
            Log.e("TAG", "progress: $progress ,fromUser: $fromUser")
            tv_main.text = (progress+20).toString()
            id_mcl_seekbar_valume.progress = progress
            Freq= progress
        }

        id_b_freq_dialog_inc.setOnClickListener {
            SYNC_INCSUB = 1
            Freq_INC_SUB(true)
        }

        id_b_freq_dialog_inc.setOnLongClickListener(object : OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                id_b_freq_dialog_inc.setStart()
                return false
            }
        })


        id_b_freq_dialog_inc.setOnLongTouchListener(object: LongCickButton.LongTouchListener{
            override fun onLongTouch() {
                SYNC_INCSUB = 1
                Freq_INC_SUB(true)
            }
        },100)

        //  =====    ---

        id_b_freq_dialog_sub.setOnClickListener {
            SYNC_INCSUB =0
            Freq_INC_SUB(false)
        }

        id_b_freq_dialog_sub.setOnLongClickListener(object : OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                id_b_freq_dialog_sub.setStart()
                return false
            }
        })


        id_b_freq_dialog_sub.setOnLongTouchListener(object: LongCickButton.LongTouchListener{
            override fun onLongTouch() {
                SYNC_INCSUB = 0
                Freq_INC_SUB(false)
            }
        },100)


    }

    private fun Freq_INC_SUB(inc: Boolean) {
        if (inc) {//递增
            if (BOOL_HP) {//高通
                if (++Freq > LP_Freq) {
                    Freq = LP_Freq
                }
            } else {
                if (++Freq > 480) {
                    Freq = 480
                }
            }
        } else {
            if (BOOL_HP) {//高通
                if (--Freq < 0) {
                    Freq = 0
                }
            } else {
                if (--Freq < HP_Freq) {
                    Freq = HP_Freq
                }
            }
        }

        id_mcl_seekbar_valume.progress = Freq
        tv_main.text = (Freq+20).toString()
    }
}
