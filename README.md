# Demo app for a StackOverflowError in dokkaHtml task

In order to reproduce the issue run the following command from the terminal:

```shell
./gradlew :shared:dokkaHtml --stacktrace
```

the result of the execution would be the following:

```
* Exception is:
org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':shared:dokkaHtml'.
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.lambda$executeIfValid$1(ExecuteActionsTaskExecuter.java:147)
        at org.gradle.internal.Try$Failure.ifSuccessfulOrElse(Try.java:282)
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeIfValid(ExecuteActionsTaskExecuter.java:145)
        ....
        at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:48)
Caused by: java.lang.StackOverflowError
        at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:384)
        at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedNotNullLazyValue.invoke(LockBasedStorageManager.java:527)
        ...
```

Changing `WeakReference` definition in `shared/androidMain` in a way it starts using import alias

```diff
package com.example.dokka.weakref

-import kotlin.native.ref.WeakReference
+import kotlin.native.ref.WeakReference as NativeWeakReference

-actual typealias WeakReference<T> = WeakReference<T>
+actual typealias WeakReference<T> = NativeWeakReference<T>
```

as well as changing `WeakReference` definition in `shared/iosMain` in the same way

```diff
package com.example.dokka.weakref

-import kotlin.native.ref.WeakReference
+import kotlin.native.ref.WeakReference as AndroidWeakReference

-actual typealias WeakReference<T> = WeakReference<T>
+actual typealias WeakReference<T> = AndroidWeakReference<T>
```

makes `StackOverflowError` disappear which is a quite good workaround for this issue, however
the code without import aliases compiles perfectly well both for iOS and Android without any
warnings.
