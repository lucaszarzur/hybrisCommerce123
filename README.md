# Hybris Commerce 123

Learn and practice Hybris with the suite **"Hybris commerce 123"**, from step by step of SAP: https://help.sap.com/viewer/3fb5dcdfe37f40edbac7098ed40442c0/1811/en-US/a1ef894ac89545e79c470c726b487d13.html

**Hybris version**: 1811 (CXCOMM181100P_4-70004085)

Hybris uses the following technologies:
* Java 8 Software Development Kit
* Apache Maven
* HSQLDB
* JUnit

### To start:
Linux:
1 - Unzip SAP Commerce (Hybris):
```sh
  $ sudo unzip CXCOMM181100P_4-70004085.zip
```


2 - Move all folders and files to "hybris_accelerator_commerce_123":
```sh
  $ sudo mv CXCOMM181100P_4-70004085/ /app/z-pessoais/hybris_accelerator_commerce_123
```

3 - Clone this repository in "/app/z-pessoais/hybris_accelerator_commerce_123/hybris";

4 - Install recipes:
```sh
  $ cd /app/z-pessoais/hybris_accelerator_commerce_123/installer
  $ . ./install.sh -r b2c_acc setup
  $ . ./install.sh -r b2c_acc initialize

  $ ./install.sh -r b2b_acc setup
  $ . ./install.sh -r b2b_acc initialize

  $ cd /app/z-pessoais/hybris_accelerator_commerce_123/hybris/bin/platform
  $ ant clean all
```

5 - Update database:
```sh
   $ cd /app/z-pessoais/hybris_accelerator_commerce_123/hybris/bin/platform && ant updatesystem
```

6 - Run the content of "loadDataThroughTheHac.impex" in the Hac;
