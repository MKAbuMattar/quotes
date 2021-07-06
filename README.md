# Quotes

## Challenge
<!-- Description of the challenge -->

read local file type JSON in java,
and convert to object to rewriting
JSON file in a pretty way,
and print random quote each time.

## Approach & Efficiency
<!-- What approach did you take? Why? What is the Big O space/time for this approach? -->



## API
<!-- Description of each method publicly available to your Linked List -->


Class Quote

have constructor each time you call it 
u need to insert `author`, `text`. 
each of them has getter and setter.

Class GetQuote

have constructor each time you call it
u need to insert path, path has getter & setter.

`getQuote()` void method to get path form 
`getPath()` and `BufferedReader` to read
the file and start to insert to list type 
`Quote` using `TypeToken` start to insert as
array list, `toJSONQuote()` to convert a list
and make it pretty and save it in new file,
`getRandomQuote()` passing list type
`Quote` it will render it and print random
quote each time.

## install

clone repo SSH
```bash
git clone git@github.com:MKAbuMattar/quotes.git

cd quotes
```

add a gson library to your gradle file

```java
dependencies {
    implementation 'com.google.code.gson:gson:2.8.7'
}
```



