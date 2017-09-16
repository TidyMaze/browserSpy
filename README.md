# browserSpy

This project is still a POC but is working.

## What?
A multi-platforms and multi-browsers tool to react to events that occure in browsers.

## Why?
We want to track user actions (click, select, fields ...) and do things with this data, in live.

## How?
Selenium with WebDrivers usually send actions to browsers, here we use them to give the browser a way to talk back to Java code.
To do so, we first start a tiny webserver (Jetty) from Java code, then we inject with webDrivers a JavaScript payload containing Event Listeners and HTTP calls.

## Pros
- Lightweight (pretty simple code and small server)
- Cross platform (thanks to Java)
- Cross browser (thanks to web drivers and Selenium)
- Simple code that is not magic

## Cons
- create a webserver and communicates through HTTP calls, which is still heavy
