# browserSpy

This project is still a POC but is working.

## What?
A multi-platforms and multi-browsers tool to react to events that occure in browsers.

## Why?
We want to track user actions (click, select, fields ...) and do things with this data, in live.

## How?
Selenium with WebDrivers usually send actions to browsers, here we use them to give the browser a way to talk back to Java code.
To do so, we first start a tiny webserver (Jetty) from Java code, then we inject with webDrivers a JavaScript payload containing Event Listeners and HTTP calls.

![This images shows the general architecture](browserSpy.png?raw=true "How it works")

## Getting started
- install drivers (chromedriver, firefoxdriver ...)
- set driver in code
- ```mvn clean install```
- ```mvn exec:java -Dexec.args="http://www.perdu.com"```
