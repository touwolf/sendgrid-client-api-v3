# Sendgrid Client Api V3

Sendgrid Client Api V3 implementation.

## API Methods Implemented

### Bounces API 

    List all bounces - GET
    Delete bounces - DELETE
    Get a bounce - GET
    Delete a bounce - DELETE
    
### Contacts API

Custom Fields

    Create a Custom Field - POST
    List all Custom Fields - GET
    Delete a Custom Field - DELETE
    List Reserved Fields - GET

Lists

    Create a List - POST
    List All Lists - GET
    Delete Multiple Lists - DELETE
    Retrieve a List - GET
    Update a List - PATCH
    Delete a List - DELETE
    List Recipients on a List - GET
    Add a single Recipient to a List - POST
    Delete a Single Recipient From a List - DELETE
    Add Multiple Recipients To a List - POST

Recipients

    Add Recipients - POST
    Update a Recipient - PATCH
    Delete one or more Recipients - DELETE
    List Recipients - GET
    Retrieve a Recipient - GET
    Delete a single Recipient - DELETE
    Get Count of Billable Recipients - GET
    Get Count of Recipients - GET
    Get Recipients Matching Search Criteria - GET

Segments

    Create a Segment - POST
    List all Segments - GET
    Retrieve a Segment - GET
    Update a Segment - PATCH
    Delete a Segment - DELETE
    List Recipients on a Segment

### Campaing API

Campaigns

    Create a Campaign - POST
    Get all Campaigns - GET
    View a Campaign - GET
    Delete a Campaign - DELETE
    Update a Campaign - PATCH
    Send a Campaign - POST
    Schedule a Campaign - POST
    Update a Scheduled Campaign - PATCH
    Unschedule a Scheduled Campaign - DELETE
    Send a Test Campaign - POST


Dependencies
============

Bridje Core IoC library https://github.com/bridje/bridje-framework/wiki/Bridje-IoC

        <dependency>
            <groupId>org.bridje</groupId>
            <artifactId>bridje-ioc</artifactId>
            <version>0.1.2</version>
        </dependency>
        


An HTTP & HTTP/2 client for Android and Java applications https://github.com/square/okhttp/wiki

          <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>3.0.1</version>
        </dependency>


--

The project is licensed under the MIT License:

The MIT License (MIT)

Copyright (c) 2016 Touwolf Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
