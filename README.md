# Mvp With Dagger2
Mvp design pattern implementation with dagger2 and rxJava.

# Mvp pattern is:
Model -> Your data class like pojo or accessing from database.
View -> View where users interact, its will delegate its task to presenter.
Presenter - > Your activty or fragment logic handing done here.

# Dagger2 uses:
We used dagger2 for making singleton object on demand. Object creation is handle by it. Also if a class depened on other class object, dependency inject done by it.

# rxJava:
While making async call for non ui thread with callback. 
