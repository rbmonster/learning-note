package com.bigdata.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.nio.charset.StandardCharsets;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: SparkDemo
 * @Author: sanwu
 * @Date: 2021/1/1 13:33
 */
public class SparkDemo {


    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setAppName("testDemo");

        SparkSession sparkSession = SparkSession.builder()
                .appName("testDemo")
                .config("spark.testing", true)
                .config("spark.driver.allowMultipleContexts", true)
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> dataset = sparkSession.read().format("csv").option("header", true)
                .option("seq", "|")
                .option("charset", StandardCharsets.UTF_8.name())
                .load("C:\\Users\\86159\\Desktop\\tmp.txt");
        System.out.println("===============>"+dataset.count());
        System.out.println(dataset.take(1));


    }


}
