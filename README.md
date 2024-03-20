# RDB-based Chat Application (Demo)

## Description

When you Google how to develop a chat application, most of the resources suggest using NoSQL databases like MongoDB or Firestore. On
the other hand, there are not many articles explaining how to build a chat application with a relational database.

Most enterprises have at least one relational database in use, and they usually don't face high traffic. Hence, a
well-designed relational database can quickly and flawlessly accomplish most tasks you want. In this context, what
benefits could you gain from excluding the relational database and introducing a new data store to develop a chat
application?

This application is a demo chat application developed based on H2 (MySQL Mode), and it shows you don't need to rule out the relational
database you've been using well and introduce a new data store to develop a chat application.

## Table of Contents

1. [ERD](#erd)
2. [Installation](#installation)
3. [Usage](#usage)

## ERD

<img width="1532" alt="image" src="https://github.com/shirohoo/chat-mysql/assets/71188307/abf48152-5ee8-4b19-937a-86d70cb1c93b">

## Installation

```shell
./gradlew bootRun
```

## Usage

Enter `localhost:8080` into your browser. 
