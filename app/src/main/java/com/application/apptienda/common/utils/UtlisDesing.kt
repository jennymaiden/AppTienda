package com.application.apptienda.common.utils

import android.content.Context
import com.application.apptienda.R

class UtlisDesing {

    companion object {

        fun getChageColor(color: String?, type: String?): Int {

            return if (type == "shirt") {
                getShirtColorImage(color)
            } else if (type == "sweater") {
                getSweaterColorImage(color)
            } else {
                R.mipmap.camisa_negra
            }
        }

        fun getShirtColorImage(color: String?): Int {
            var shirtColor = R.mipmap.camisa_negra
            when (color) {
                "White" -> {
                    shirtColor = R.mipmap.camisa_blanca
                }
                "Black" -> {
                    shirtColor = R.mipmap.camisa_negra
                }
                "Blue" -> {
                    shirtColor = R.mipmap.camisa_azul
                }
                "Red" -> {
                    shirtColor = R.mipmap.camisa_roja
                }
            }
            return shirtColor
        }

        fun getSweaterColorImage(color: String?): Int {
            var sweaterColor = R.mipmap.buzo_negro
            when (color) {
                "White" -> {
                    sweaterColor = R.mipmap.buzo_blanco
                }
                "Black" -> {
                    sweaterColor = R.mipmap.buzo_negro
                }
                "Blue" -> {
                    sweaterColor = R.mipmap.buzo_azul
                }
                "Red" -> {
                    sweaterColor = R.mipmap.buzo_rojo
                }
            }
            return sweaterColor
        }

        fun getDesing(desing: Int?, type: String?, color: String?, context: Context): Int {
            val packageName = context?.packageName ?: ""
            val test = "${type}_${color}_animado_${desing}"
            return context.resources.getIdentifier(test, "mipmap", packageName)
        }

        fun getDesing(product: String?, context: Context): Int {
            val packageName = context?.packageName ?: ""
            return context.resources.getIdentifier(product, "mipmap", packageName)
        }

        fun getTypeUppercase(type: String?): String {
            var answer = ""
            when (type) {
                "shirt" -> {
                    answer = "SHIRT"
                }

                "sweater" -> {
                    answer = "SWEATER"
                }
            }
            return answer
        }

        fun getColorUppercase(color: String?): String {
            var answer = ""
            when (color) {
                "black" -> {
                    answer = "BLACK"
                }
                "white" -> {
                    answer = "WHITE"
                }
                "red" -> {
                    answer = "RED"
                }
                "blue" -> {
                    answer = "BLUE"
                }
            }
            return answer
        }

    }
}