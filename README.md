# MultiSimSMS

This is a simple Android Library that will enable developers to add multi Sim SMS functionality in android. When Implemented it will display a dialog asking users to choose the SIM from which they want to send the SMS. It will automatically send SMS from the one sim if the other SIM is not available or has 'No Service' without displaying the dialog. The dialog feature will work only on devices having build version equal to or more than Marshmallow. However this library will still work on those devices but the dialog won't show.

#HOW TO SETUP?
To use this Library in your project you first need to: 

1) Add this to your root ```build.gradle``` file:  
```groovy
allprojects{  
    repositories {  
        ......  
        maven {
        
        url 'https://jitpack.io'//TODO  
        
        }   
    }    
}      
```  
2) And then in your Project's ```build.gradle``` file:  
```groovy  
dependencies {  
    .......  
    
    compile 'com.github.aamirwahid5:MultiSimSMS:1.0' //TODO  
       
}  
```
#HOW TO USE:  
1) Add these permissions in your project's manifest file:
```groovy  
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.SEND_SMS"/>  
```
__Remember: You need to provide the permission at runtime also else the app will crash.( Goto app Settings > Permissions > Turn On)__  

2) Then simply Call this inside your Button or wherevever you want :)    
```java  
        MultiSimSMS.initialize(this);  
        MultiSimSMS.setNumber("123456789"); //your destination number  
        MultiSimSMS.setMessage("Hello");    //your message  
        MultiSimSMS.sendMessage(new MultiSimSMS.setOnSMSListener() {   //you can also use it without listener
            @Override  
            public void onSmsSent() {
                Toast.makeText(MainActivity.this, "SMS Sent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGenericFailure() {  
            //Do Something on Generic Failure  
            }

            @Override
            public void onNoService() {  
            //Do Something on No Service    
            }

            @Override
            public void onNullPdu() {  
            //Do Something on Null Pdu  
            }

            @Override
            public void onRadioOff() {  
            //Do Something on Radio Off    
            }

            @Override
            public void onInvalidDestinationAddress() {  
            //Do Something if Invalid Destination Address is provided or Field is blank  
            }

            @Override
            public void ServiceAvailOnSingleSim(CharSequence charSequence, int i) {  
            //Do Something if Service is availaible on single sim

            }

            @Override
            public void SingleSimOrLessBuild() {
            //Do Something if the mobile is only Single sim or build version is less than Marshmallow  
            }
        });
    }  
    ```
    License
    -------
    Copyright 2013 Sheikh Aamir Wahid
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
       http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
