#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_advertising_1id_1service_appclick_googleadvertisingidservice_GlobalParameters_stringFromJNI(
        JNIEnv *env, jobject instance) {
    char ARG[58] = { 0 };
    ARG[0] = '6';
    ARG[1] = '7';
    ARG[2] = '8';
    ARG[3] = '9';
    ARG[4] = '0';
    ARG[6] = 'Q';  ARG[32] = 'q';
    ARG[7] = 'W';  ARG[33] = 'w';
    ARG[8] = 'E';  ARG[34] = 'e';
    ARG[9] = 'R';  ARG[35] = 'r';
    ARG[10] = 'T'; ARG[36] = 't';
    ARG[11] = 'Y'; ARG[37] = 'y';
    ARG[12] = 'U'; ARG[38] = 'u';
    ARG[13] = 'I'; ARG[39] = 'i';
    ARG[14] = 'O'; ARG[40] = 'o';
    ARG[15] = 'P'; ARG[41] = 'p';
    ARG[16] = 'A'; ARG[42] = 'a';
    ARG[17] = 'S'; ARG[43] = 's';
    ARG[18] = 'D'; ARG[44] = 'd';
    ARG[19] = 'F'; ARG[45] = 'f';
    ARG[20] = 'G'; ARG[46] = 'g';
    ARG[21] = 'H'; ARG[47] = 'h';
    ARG[22] = 'J'; ARG[48] = 'j';
    ARG[23] = 'K'; ARG[49] = 'k';
    ARG[24] = 'L'; ARG[50] = 'l';
    ARG[25] = 'Z'; ARG[51] = 'z';
    ARG[26] = 'X'; ARG[52] = 'x';
    ARG[27] = 'C'; ARG[53] = 'c';
    ARG[28] = 'V'; ARG[54] = 'v';
    ARG[29] = 'B'; ARG[55] = 'b';
    ARG[30] = 'N'; ARG[56] = 'n';
    ARG[31] = 'M'; ARG[57] = 'm';

    return (*env)->NewStringUTF(env, "its jni");
}

JNIEXPORT jstring JNICALL
Java_com_advertising_1id_1service_appclick_googleadvertisingidservice_GlobalParameters_getPassToCert_1ndk(
        JNIEnv *env, jobject instance) {
    char str_pass[6] = { 0 };
    str_pass[0] = '1';
    str_pass[1] = '2';
    str_pass[2] = '3';
    str_pass[3] = '4';
    str_pass[4] = '5';
    str_pass[5] = '\0';
    return (*env)->NewStringUTF(env, str_pass);
}

JNIEXPORT jstring JNICALL
Java_com_advertising_1id_1service_appclick_googleadvertisingidservice_GlobalParameters_getCert_1ndk(
        JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "308209990201033082095F06092A864886F70D010701A08209500482094C30820948308203FF06092A864886F70D010706A08203F0308203EC020100308203E506092A864886F70D010701301C060A2A864886F70D010C0106300E0408B980BC73AC807C4702020800808203B89F1FCA31A28FD6D151D11C8849F89C0B5FAFAEE0305A2F11234EB3BE9B19D2E8D7E8A334D39780EF22B62FDD6B5E7CAAAC43119A2EBA7BD16F60CD549293149BB0216E9F42B6F3D640AD65881E512F46BAE6CC731BE19FF37875F70C786CE9FBE88986378A1A2F13AE814992FA1AA7DF355F05C61AB1A9183AE8FD8C5EC267C1D237B1C6574BDA665195B4F780FBB020C526A57B8C4C27BB3E471AB04114F9323335F551B8690C5DC8DD96212509F3D46E21614DCCD13668B0B8689AC7887B83415B4FDE61E4A5DE438D2C1CA613329AFFAD3FC1670B451F33B117591937D29116DEA57491FE80669F438FDE78FE732176F203D4BD379D678C25C6F51398B6FA9410703ACEFB74D47FA867B40F063535501C280E7B11B707D09657E0D678A3F5D2AB70C5D40FB1A8919E7D9AB1ACD1A829F0818B08F10845CBA559C2B5A9011EF0BEB103A2AB041D16E3DBBF3B74E5B8D2F558F93280F247365B7E3805BB46F1A695E7957050075DD43A6211026B98429FCE33932BF895407DDE5428E736C60BD1C8048BEA81D42E275A372C07734A9EEAF1EB6F412987D94505BAE834F7F661F3F37ED22748E11CE3DF1A44B3E9E969DFF1B1F39FFBB16A279F4CB6E12CA68ADDE54D22111610A3F02D7F95D0F61183EC509FCC2A7B0DBF66B90B7357AEFE5F146A7C0B195108B9F91CA04572C95F4D8D5D1D2D290BD840D41A47CF52BB65B5E9488C671A934F3564860B1DEC0CA12B08C220CF80287DEC27A35C5CE3E2DAB6A67B6FF1D51F4131895EF9BF25A4A58F7EE19123EC98FCC7CB7F698A029B736AF7C2CBE92D4DFEBD817DF3B2F13BF92EC3179073351C39F5A47F4DD161136FEA0EC40F1A8560DEB6E7F12FE5BAD0CF5F3B264321414200252B26AE7C2314A2F9F62BC3867C65C5AB5403F3E752A972270AEB0E7C0317E57A83395C87C2C0888ABE272356F1D4948194D792E53CCD2BEEA735B6AB424069AC2A5A0312FFA330BA33D363998E81F502FFB689C4ECF895B8891743851498D0AE0FB735D6A93C5FBA364C2B34E12EAEC5F97B9D45069100CD6ACA2D48D5924287FEF68D7E55330F3EB5B3A2E6A459C4EAA460C4E668EA177EB9CFD898BA10BE23BFB1E6082D302DD6B580E52FB0B03717799796D1F732448BFF89B873DC86D5D5B65BED564EF3A355F2B82A9BCCBF35A071F28D33F627DDC2580F4A5F10051AEF6F7E8110E2402099304E33234815F39930063824D6D1FFB7574984C1724FEECE172AD108CF0470CCDE8C115634B29C0A364ECCF25FC166009BA3A136C26BDB67D2092CCEE4B85E7C6E2C28D4733FF8ABA19CE717CCD688B41DD7A44D2855D4A63082054106092A864886F70D010701A08205320482052E3082052A30820526060B2A864886F70D010C0A0102A08204EE308204EA301C060A2A864886F70D010C0103300E04080415DAF2A93EFC5B02020800048204C89D21E46D68F512104BA368779D42B7113D7BEB384199D1F66932538F4FD571FFAE0D95E8FD368FCE9A687F5DE2754B4F3E0DAEC365010135863E626E9A85D26C07EAC28E8DB92F29DDDF1CA3F9BCA24486CF96568543DF2F5C4FEDCEFEA2F46AC80088BB53C3BD39B9E245E65EF496472BA88E1F219B8F50BE50C3779C9822B3F45D00B6F29FE3E7AB6685C707025C768D177B9E21CBD9B2645E9F35365133F56AEE55EC986CC4BFF1653322506371D96E10A47475D730F40F8389B4C2B53DA661FCEAA6A87E49A2618A433709D0E1732A481B2B2DA502DD557C5257A84C103A8B164DEFFB452024926E5D646B23A7218523B65A9C93F744B9CC47211E3C3256D89BE300980A36FFC5B1849111E8151F4141B099316F3DD2C5D60ACA50D33899DCE59AA0EC88C700CDA1C9240CA712928F1E81868B0F059D5F4B3E4BB19A1F929B7B02870D94A9AE99D0F609C818618440D1D04944A2698011FDBADE83929FBFA68CB54A2AF8C6ABA8061B307486419F40BEF6358ECBA0B348CBD2E7C6ACB2B6872683064D98D2293A39CC63F7D5646B97B803FECA124AD596CD1B0EF29852428756CCD6DF16939681ABC000F98781F5F20113E5146D72BB5A95148FEA0DA6C55FEE99729D08ED3BB2984F6B2BC54670955A4E17895A686E8E01077A17D11351EB88B63FAE3CB6EEF4270DC266114281C0FA25E8097814C35360F498E9AEA64BA341B2E583567BF4D79E2E2145C6C8C80E40CAF446EC2CD67AA60D1346446B8D4C8CE29F28900F5C4DC5004D08D48421D2EDC9C300C6A97F70C60C9D97D00755A0A470A7BBA10793A58EB4A9B53BE43FB683D3B6AAB7983751BE043AEDF9C0A7A406F5DC2E3472F130DDF2E9755DFF280BDCBB69C9EE15F4CA779A29ADC81D420F5C555EFC005D198E789107A0353ED1E82461C1FCE72557635EDD97678357ECC82801E22D248749D85E3EF0711F0D7361E28AE15E997B23D937C59E74AF7B6A0F865DAF4691D732E6F8B2743BBBA54C935472124D88072114F356DE97B64B55E4F90C5DC66FB9720145129D1AB5FF85CB4999EFEF122B0D27822D9E9B8C31B56C8943B231E9D3225814C018479DF5B43E1D3B441A6ABA6363A7BC1E997ADF095E59433A653FC694DBCBD4D30996AB7FAAA2ECAC7E2512C4E522CDE43CA01B008A0458138E47C896AD22D400AF36EA07FF28882DA75ABCF7154363CC9592C61EF7AE030EA539ECBB6CA8C3D19F0F1345BC96CFA8F83AEF624FCF3A7AEA36BD0D3FAC4C8AAB8D03734B848F6C6AB58FC16BF3067E229410B4C356CDE223D1C9901B4A6D5B7BFA3CE0813CCEF6BA23E27DF3DB46DB3AD95AC95C888EBB53352982FB12064766ACA3DB8E7092EA0BF8920B8A129A029F76DDEE6DC7A853EAF2AEE9F2E05C09A63ADE4549F9A23D13574CB8D38FB5DA7A4AC3465C121FD96B7D7BBB095C470EC753ED0B9FF18A52A1600DFA5132420AFFBDD25EBA3F238FD80534C48D3975521E0C1904D864F9090223900019C1067577D3F14D712953133BBE5DD8D8C22E4F6FA53183291A10F881664CE3F7205376C2B1641A4728E04A7599B4C56ADA00A519C3AA4E28869D67768E23D8BF9AA8E69F7F954A6A4C5179B1D9CFFB250DC92820F9056C79BD75FC6736F263AF42D07652F80E69C0AE34B132C926B7A7688D3483FD7CDD239872779B16919E00E7BE218BC3C9BF7FC495D24F50ED02E1257AFB3AE87A9F3125302306092A864886F70D0109153116041412498E19B136967341FB61C6FF2A397BF0517FB030313021300906052B0E03021A05000414CBC49C080394DB2B57D2369F1CC3D7F4F58006C604084D98AC530490A96B02020800");
}

JNIEXPORT jstring JNICALL
Java_com_advertising_1id_1service_appclick_googleadvertisingidservice_CryptoProvider_CryptoProviderServicer_codeFromJNI(
        JNIEnv *env, jclass type, jstring str_) {
    const char *str = (*env)->GetStringUTFChars(env, str_, 0);

    //******************* скроываем в бинарнике String-овые строки *********************************
    char str_blowfish[9] = { 0 };
    str_blowfish[0] = 'B';
    str_blowfish[1] = 'L';
    str_blowfish[2] = 'O';
    str_blowfish[3] = 'W';
    str_blowfish[4] = 'F';
    str_blowfish[5] = 'I';
    str_blowfish[6] = 'S';
    str_blowfish[7] = 'H';
    str_blowfish[8] = '\0';
    //**********************************************************************************************

    jclass clazz_test    = (*env)->FindClass(env, "com/advertising_id_service/appclick/googleadvertisingidservice/CryptoProvider/CryptoProviderServicer");
    jclass clazz_JString = (*env)->FindClass(env, "java/lang/String");
    jmethodID m_getBytes = (*env)->GetMethodID(env, clazz_JString, "getBytes", "()[B");
    jmethodID m_toHex    = (*env)->GetStaticMethodID(env, clazz_test, "toHex", "([B)Ljava/lang/String;");

    jbyte a[16] = {0}; //{'!', 'A', '3', 'v', 'O', '6', 'q', 's', 'p', '2', '@', '#', 'U', 'a', '1', '['};
    a[0] = '!';
    a[1] = 'A';
    a[2] = '3';
    a[3] = 'v';
    a[4] = 'O';
    a[5] = '6';
    a[6] = 'q';
    a[7] = 's';
    a[8] = 'p';
    a[9] = '2';
    a[10] = '@';
    a[11] = '#';
    a[12] = 'U';
    a[13] = 'a';
    a[14] = '1';
    a[15] = '[';
    jbyteArray ret = (*env)->NewByteArray(env, 16);
    (*env)->SetByteArrayRegion (env, ret, 0, 16, a);

    jclass    clKey     = (*env)->FindClass(env, "javax/crypto/spec/SecretKeySpec");                               // |
    jmethodID keyConstr = (*env)->GetMethodID(env, clKey, "<init>", "([BLjava/lang/String;)V");                    // | SecretKeySpec key = new SecretKeySpec(seed.getBytes("utf-8"), "BLOWFISH");
    jobject   key       = (*env)->NewObject(env, clKey, keyConstr, ret, (*env)->NewStringUTF(env, str_blowfish));  // |

    jclass    clCipher      = (*env)->FindClass(env,"javax/crypto/Cipher");                                                           // |
    jmethodID m_getInstance = (*env)->GetStaticMethodID(env,clCipher, "getInstance", "(Ljava/lang/String;)Ljavax/crypto/Cipher;");    // | Cipher ecipher = Cipher.getInstance("BLOWFISH");
    jobject   ecipher       = (*env)->CallStaticObjectMethod(env, clCipher, m_getInstance, (*env)->NewStringUTF(env, str_blowfish));  // |

    jmethodID m_init = (*env)->GetMethodID(env,clCipher, "init", "(ILjava/security/Key;)V"); //|
    (*env)->CallVoidMethod(env, ecipher, m_init, 1, key);                                    //| //ecipher.init(Cipher.ENCRYPT_MODE, key);

    jmethodID  m_doFinal = (*env)->GetMethodID(env, clCipher, "doFinal", "([B)[B");                                                                                   //|
    jbyteArray enc       = (jbyteArray) (*env)->CallObjectMethod(env, ecipher, m_doFinal, (*env)->CallObjectMethod(env, (*env)->NewStringUTF(env, str), m_getBytes)); //| byte[] enc = ecipher.doFinal(src.getBytes());

    jstring result_str = (jstring) (*env)->CallStaticObjectMethod(env, clazz_test, m_toHex, enc); // | toHex(enc)


    (*env)->ReleaseStringUTFChars(env, str_, str);
    return result_str;
}

JNIEXPORT jstring JNICALL
Java_com_advertising_1id_1service_appclick_googleadvertisingidservice_CryptoProvider_CryptoProviderServicer_decodeFromJNI(
        JNIEnv *env, jclass type, jstring str_) {
    const char *str = (*env)->GetStringUTFChars(env, str_, 0);
    //******************* скроываем в бинарнике String-овые строки *********************************
    char str_blowfish[9] = { 0 };
    str_blowfish[0] = 'B';
    str_blowfish[1] = 'L';
    str_blowfish[2] = 'O';
    str_blowfish[3] = 'W';
    str_blowfish[4] = 'F';
    str_blowfish[5] = 'I';
    str_blowfish[6] = 'S';
    str_blowfish[7] = 'H';
    str_blowfish[8] = '\0';
    //**********************************************************************************************

    jclass    clazz_JString = (*env)->FindClass(env, "java/lang/String");
    jclass    clazz_test    = (*env)->FindClass(env, "com/advertising_id_service/appclick/googleadvertisingidservice/CryptoProvider/CryptoProviderServicer");
    jmethodID m_toByte      = (*env)->GetStaticMethodID(env, clazz_test, "toByte", "(Ljava/lang/String;)[B");

    jbyte a[16] = {0}; //'!', 'A', '3', 'v', 'O', '6', 'q', 's', 'p', '2', '@', '#', 'U', 'a', '1', '['};
    a[0] = '!';
    a[1] = 'A';
    a[2] = '3';
    a[3] = 'v';
    a[4] = 'O';
    a[5] = '6';
    a[6] = 'q';
    a[7] = 's';
    a[8] = 'p';
    a[9] = '2';
    a[10] = '@';
    a[11] = '#';
    a[12] = 'U';
    a[13] = 'a';
    a[14] = '1';
    a[15] = '[';
    jbyteArray ret = (*env)->NewByteArray(env, 16);
    (*env)->SetByteArrayRegion (env, ret, 0, 16, a);

    jclass    clKey     = (*env)->FindClass(env, "javax/crypto/spec/SecretKeySpec");                              // |
    jmethodID keyConstr = (*env)->GetMethodID(env, clKey, "<init>", "([BLjava/lang/String;)V");                   // | SecretKeySpec key = new SecretKeySpec(seed.getBytes("utf-8"), "BLOWFISH");
    jobject   key       = (*env)->NewObject(env, clKey, keyConstr, ret, (*env)->NewStringUTF(env, str_blowfish)); // |

    jclass    clCipher      = (*env)->FindClass(env, "javax/crypto/Cipher");                                                        // |
    jmethodID m_getInstance = (*env)->GetStaticMethodID(env, clCipher, "getInstance", "(Ljava/lang/String;)Ljavax/crypto/Cipher;"); // | Cipher ecipher = Cipher.getInstance("BLOWFISH");
    jobject   ecipher       = (*env)->CallStaticObjectMethod(env, clCipher, m_getInstance, (*env)->NewStringUTF(env, str_blowfish));// |

    jmethodID m_init = (*env)->GetMethodID(env, clCipher, "init", "(ILjava/security/Key;)V"); //|
    (*env)->CallVoidMethod(env, ecipher, m_init, 2, key);                                     //| ecipher.init(Cipher.DECRYPT_MODE, key);


    jmethodID  m_doFinal = (*env)->GetMethodID(env, clCipher, "doFinal", "([B)[B");                                                                                                   //|
    jbyteArray dec       = (jbyteArray) (*env)->CallObjectMethod(env, ecipher, m_doFinal, (*env)->CallStaticObjectMethod(env, clazz_test, m_toByte, (*env)->NewStringUTF(env, str))); //| byte[] dec = ecipher.doFinal(toByte(src));


    jmethodID JStringConstr = (*env)->GetMethodID(env, clazz_JString, "<init>", "([B)V"); //|
    (*env)->ReleaseStringUTFChars(env, str_, str);                                        //| return new String(dec);
    return (jstring) (*env)->NewObject(env, clazz_JString, JStringConstr, dec);           //|
}

JNIEXPORT jstring JNICALL
Java_com_advertising_1id_1service_appclick_googleadvertisingidservice_DeviceInfo_getSig(JNIEnv *env,
                                                                                        jclass type,
                                                                                        jobject cnt) {

    jclass    clazz_test    = (*env)->FindClass(env, "com/advertising_id_service/appclick/googleadvertisingidservice/CryptoProvider/CryptoProviderServicer");
    jmethodID m_getSig      = (*env)->GetStaticMethodID(env, clazz_test, "getSig", "(Landroid/content/Context;)Ljava/lang/String;");
    jobject   result        = (*env)->CallStaticObjectMethod(env, clazz_test, m_getSig, cnt);
    return (jstring) result;
}


JNIEXPORT jstring JNICALL
Java_com_advertising_1id_1service_appclick_googleadvertisingidservice_CryptoProvider_CryptoProviderServicer_codeUrlParamsJNI(
        JNIEnv *env, jclass type, jobject cnt, jstring str_) {

    const char *str = (*env)->GetStringUTFChars(env, str_, 0);

    jclass    clazz_test  = (*env)->FindClass(env, "com/advertising_id_service/appclick/googleadvertisingidservice/CryptoProvider/CryptoProviderServicer");
    jmethodID m_getSig    = (*env)->GetStaticMethodID(env, clazz_test, "getSig", "(Landroid/content/Context;)Ljava/lang/String;");
    jobject   sign        = (*env)->CallStaticObjectMethod(env, clazz_test, m_getSig, cnt);
    const char *strinp    = (*env)->GetStringUTFChars(env, sign, 0);
    int j = 0;
    int str_size = 1;
    while (str[j] != '\0'){j++;str_size++;}
    int strinp_size = 1;
    j = 0;
    while (strinp[j] != '\0'){j++;strinp_size++;}

    j = 0;
    char str_res[(str_size + strinp_size)];
    for (int i = 0 ; i < str_size; i++)
    {
        if (i != str_size-3)
        {
            str_res[j] = str[i];
            j++;
        } else{
            str_res[j] = str[i];
            j++;
            str_res[j++] = ' ';
            str_res[j++] = '"';
            str_res[j++] = 's';
            str_res[j++] = 'i';
            str_res[j++] = 'g';
            str_res[j++] = 'n';
            str_res[j++] = '"';
            str_res[j++] = ':';
            str_res[j++] = '"';
            for (int k = 0 ; k < strinp_size-1; k++)
            {
                str_res[j] = strinp[k];
                j++;
            }
            str_res[j++] = '"';
        }
    }
    str_res[j] = '\0';

    (*env)->ReleaseStringUTFChars(env, str_, str);
    (*env)->ReleaseStringUTFChars(env, sign, strinp);
    return Java_com_advertising_1id_1service_appclick_googleadvertisingidservice_CryptoProvider_CryptoProviderServicer_codeFromJNI(env, type, (*env)->NewStringUTF(env, str_res)); // - правильная строчка

    ////    return (*env)->NewStringUTF(env, "its jni3");
////    return Java_com_advertising_1id_1service_appclick_googleadvertisingidservice_CryptoProvider_CryptoProviderServicer_codeFromJNI(env, type, str_);

}