package com

def filter(){
    println("filter data")
    return "result"
}

def filter(person,id){
    println("start to call filter, param{person:" + person + ", id:" + id + "}");
}