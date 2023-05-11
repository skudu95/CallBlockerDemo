package com.kudu.callblockerdemo

import android.telecom.Call
import android.telecom.CallScreeningService
import android.widget.Toast

class CallService : CallScreeningService() {

    override fun onScreenCall(callDetails: Call.Details) {
        val phoneNumber = getPhoneNumber(callDetails)
        var response = CallResponse.Builder()
        response = handlePhoneCall(response, phoneNumber)

        respondToCall(callDetails, response.build())
    }

    private fun handlePhoneCall(response: CallResponse.Builder, phoneNumber: String): CallResponse.Builder {
        if(phoneNumber != null){
            response.apply {
                setRejectCall(true)
                setDisallowCall(true)
                Toast.makeText(applicationContext, "Call rejected from $phoneNumber", Toast.LENGTH_LONG).show()
            }
        }

        return response
    }

    private fun getPhoneNumber(callDetails: Call.Details): String{
        return callDetails.handle.toString()
    }
}