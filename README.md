# Easy Office!

### How to install?

**Step 1.** Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
**Step 2.** Add the dependency

	dependencies {
	        implementation 'com.github.sadighasanzade:easyoffice:2.0'
	}

 ### How to use?
 
    val data = FakeDataSource.getFakeData()
    val path = ExcelBuilder(this@MainActivity)
        .setFileName("report_a.xls")
        .setData(data)
        .build()
- data should be Map<String, List<String>>
- build() function convert data to excel file and save it to external storage of the device, finally returns absolute path of the file

### Example app




https://github.com/sadighasanzade/easyoffice/assets/59266227/eb270312-c406-4726-87fd-25aabe8991ce


                            
