# SealedClasses Testing Playground

It's a Kotlin project to show how to include **sealed classes** in your tests.

## Getting Started

Check ```InstagramErrorType.kt``` in order to understand the context of the whole playground.
Within that file, I've defined a sealed class ```InstagramErrorType``` with 5 different data classes.
All of them are Instagram Errors, in order to make this exercise more understandable and familiar.

## Goal

Showing and verifying the possibility of creating JUnit tests (parameterized or simple tests) using
any type of ***sealed classes*** through Factories provided by Danny Preussler in his repository [junit5-kotlin].

[junit5-kotlin]: https://github.com/dpreussler/junit5-kotlin

## Use Case

In an Android project, or a Kotlin library, we might need to test the type of sealed class we expected
in a View Model, a Mapper or within any other class.

## What did I do

I created a specific Factory for the ```InstagramType``` and I added new ways of classifying the expected
classes. In one of those (```ErrorCode```, an enum class), I decided to create an annotation in order
to identify the enum class in a more fancy way.

#  License

Copyright 2020 Fernando Prieto Moyano

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


