# MVVM JAVA
A simple Android Application  (a typical master/detail app)  based on <b>MVVM clean architecture</b>.

<br>

#### App Features
* Users can view list of the articles in a recycleview.
* Users can tap on any item from recycleview to see the details.



#### App Architecture


* A local database used for data caching.
* A web api service.
* A ViewModel that provides data specific for the UI.
* The UI, which shows a visual representation of the data in the ViewModel.
* Unit Test cases for ViewModel.
* Some UI Test cases for Recycleview.


#### App Packages
* <b>base</b> - contains base classes
* <b>databinding</b> - contains binding adapters for data binding
* <b>di</b> - contains dependency injection classes, using Dagger2.
* <b>repository</b> - contains the api classes to make api calls to NYtimes server, using Retrofit.
* <b>room</b> - contains the db classes to create/read/update caching, using Room.
* <b>util</b> - contains some util classes.
* <b>views</b> - contains classes needed to display Activity and Fragment.



#### App Specs
* Minimum SDK 21
* [Java8](https://java.com/en/download/faq/java8.xml)
* MVVM Architecture
* Android Architecture Components (LiveData, Lifecycle, ViewModel, Room Persistence Library)
* [RxJava2](https://github.com/ReactiveX/RxJava) for implementing Observable pattern.
* [Dagger 2](https://google.github.io/dagger/) for dependency injection.
* [Retrofit 2](https://square.github.io/retrofit/) for API integration.
* [Gson](https://github.com/google/gson) for serialisation.
* [Okhhtp3](https://github.com/square/okhttp) for implementing interceptor, logging and mocking web server.
* [Mockito](https://site.mockito.org/) for unit test cases
* [Glide](https://github.com/bumptech/glide/) for image loading.
* [Espresso](https://developer.android.com/training/testing/espresso) for UI testing.

#### Some useful scripts
* <b>Build the project</b>
	1.  open command line
	2. go to project directory  <code>cd path/to/project/directory</code>
	3. run <code>./gradlew clean assembleDebug</code> to clean and build debug apk. You can find the apk here <code>project_name/module_name/build/outputs/apk/</code>
* <b>Run lint code analysis</b>
	1.  open command line
	2. go to project directory  <code>cd path/to/project/directory</code>
	3. run <code>./gradlew lint</code>
	4. You should see links for the reports in the output.
* <b>Run Unit Tests</b>
	1. Connect android device with debug enabled to you pc
	2.  open command line
	3. go to project directory  <code>cd path/to/project/directory</code>
	4. run <code>./gradlew connectedAndroidTest</code>
	5. HTML test result files: <code>path_to_your_project/module_name/build/reports/androidTests/connected/</code>
	XML test result files: <code>path_to_your_project/module_name/build/outputs/androidTest-results/connected/</code>
* <b>Run Sonarqube</b>
	1. You should configure Sonarqube server before generating the report. Follow this  [link](https://blog.setapp.pl/sonarqube_introduction) for more details.
	2.  Generate a token for your Android Application by providing a name for your token in Sonarqube concole.
	3. open command line
	4. go to project directory  <code>cd path/to/project/directory</code>
	5. run <code>./gradlew sonarqube -Dsonar.host.url=SONAR_QUBE_SERVER_URL -Dsonar.login=$REPLACE_WITH_GENERATED_TOKEN</code>
	5. Visit “SONAR_QUBE_SERVER_URL/projects” after the build is successful to see the full report
