metaknight
====

![](https://c4.staticflickr.com/4/3581/3820623811_ac91816044_z.jpg?zz=1)

## Description

metaknight is hero of your test.

## Usage

### Get Sub Classes

```java
List<Class<?>> foundClasses = PackageScanner.searchSubClasses(
    getInstrumentation().getTargetContext(),
    Model.class);
```

### Instantiation

```java
Recipe recipe = new Something()
    .set("id", 1)
    .set("name", "Monkey Bread")
    .realize(Recipe.class);
recipe.getId(); // => 1
recipe.getName(); // "Monkey Bread"
```

## Install

```java
// settings.gradle
include ':app', ':..:metaknight:library'

// app/build.gradle
compile project(':..:LogHouse:library')
```
