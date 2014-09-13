metaknight
====

![](https://c4.staticflickr.com/4/3581/3820623811_ac91816044_z.jpg?zz=1)

## Description

metaknight is the hero of your test.

## Usage

### Object Instantiation

```java
Recipe recipe = new Something()
    .set("id", 1)
    .set("name", "Monkey Bread")
    .realize(Recipe.class);
recipe.getId(); // => 1
recipe.getName(); // "Monkey Bread"
```

### Overwrite Object's Field

```java
Recipe recipe = new Recipe();
ClassUtils.overwriteField(recipe, "name", "Perfect Salmon");
ecipe.getName(); // => "Perfect Salmon"
ClassUtils.overwriteField(recipe, "name", "Texas Crab Salad");
recipe.getName(); // => "Texas Crab Salad"
```

### Get Sub Classes

```java
List<Class<?>> foundClasses = PackageScanner.searchSubClasses(
    getInstrumentation().getTargetContext(),
    Model.class);
```

## Install

```java
// settings.gradle
include ':app', ':..:metaknight:library'

// app/build.gradle
compile project(':..:metaknight:library')
```
