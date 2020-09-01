<?php
 $privateKey = "1234123412341324";
 $iv    = "1234123412341324";
 $data  = "测试用的数据";

 //加密
 $encrypted = mcrypt_encrypt(MCRYPT_RIJNDAEL_256, $privateKey, $data, MCRYPT_MODE_CBC, $iv);
 echo(base64_encode($encrypted));
 echo '<br/>';

 //解密
 $encryptedData = base64_decode(base64_encode($encrypted));
 $decrypted = mcrypt_decrypt(MCRYPT_RIJNDAEL_256, $privateKey, $encryptedData, MCRYPT_MODE_CBC, $iv);
 echo($decrypted);
?>