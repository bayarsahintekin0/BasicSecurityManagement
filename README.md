# Basic Storage Management Codelab


## Table of Contents

 * [Introduction](#introduction)
 * [Installation](#installation)
 * [Configuration ](#configuration )
 * [Supported Environments](#supported-environments)
 * [Sample Code](#SampleCode)
 * [License](#license)
 
 
## Introduction

    Basic Storage Management is basically provide us to save private data in a very secure place(TEE) on Huawei Phones.
	 You can save:
	• Uses’s email and password information.
	• User’s credit card information.
	• User’s private token.
	• Everything you want.

## Installation

    First of all, you need to check whether the Android Studio environment has been installed. 
    Download the Basic Storage Management project by zip or clone in Github.
    Wait for the gradle build in your project.
    
## Supported Environments
	•	Android 4.2 and later
	•	Java JDK 1.8 or later version
	•	Android API Level 28 or higher version
	

## Configuration 

    Basic Storage Management need 'securitykit-1.0.1.aar' libaray. All you need download and add into libs package. After this you able to use all capabilities.
	
	
## Sample Code

    We are using a helper class for all asset operations : BasicStorageManagementHelper.kt 

    1-> Initiliaze HwAssetManager instance
	You can need this instance manage asset operations.

    2-> Asset Insert
	You can save your private data using hwAssetManager.assetInsert() method.

    3-> Asset Update
	You can update exist asset's properties using hwAssetManager.assetUpdate() method.

    4-> Asset Select
	You can get all saved assets as a list using hwAssetManager.assetSelect() method.

    5-> Assed Delete
	You can delete a saved asset using hwAssetManager.assetDelete() method.

##  License
    Cloud Storage Codelab is licensed under the [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
