package dev.scx.format.json.test;

import dev.scx.format.FormatToNodeException;
import dev.scx.format.NodeToFormatException;
import dev.scx.format.json.JsonNodeConvertConfig;
import dev.scx.node.Node;
import org.testng.Assert;
import org.testng.annotations.Test;

import static dev.scx.format.json.JsonNodeConverter.DEFAULT_JSON_NODE_CONVERTER;

public class JsonFormatTest {

    private final static String json = """
        {
          "user": {
            "id": 12345,
            "name": "小明",
            "nickname": "明哥\\uD83D\\ude80",
            "active": true,
            "score": 99.99,
            "address": {
              "city": "北京",
              "zipcode": "100000",
              "coordinates": {
                "lat": 39.9042,
                "lng": 116.4074
              }
            },
            "tags": ["程序员", "摄影师", "旅行者"],
            "metadata": {
              "created_at": "2025-07-09T12:34:56Z",
              "updated_at": null,
              "roles": ["admin", "editor", {"custom": "superuser"}]
            }
          },
          "posts": [
            {
              "id": "post-001",
              "title": "第一篇文章",
              "content": "这是第一篇文章的内容，包含一些 <b>HTML</b> 标签。",
              "comments": [
                {"user": "小红", "message": "写得很好！"},
                {"user": "小刚", "message": "赞👍"}
              ]
            },
            {
              "id": "post-002",
              "title": "第二篇文章",
              "content": "这是第二篇文章，内容更丰富。",
              "comments": [[1, 2, 3], [4, 5, 6]]
            }
          ],
          "config": {
            "theme": "dark",
            "notifications": {
              "email": true,
              "sms": false,
              "push": true
            },
            "experimental": [true, false, null, "beta"]
          },
          "misc": [
            123,
            "字符串",
            null,
            {
              "nested": {
                "array": [1, 2, 3, {"deep": "value"}]
              }
            }
          ]
        }
        """;

    public static void main(String[] args) throws FormatToNodeException, NodeToFormatException {
        test1();
        test2();
    }

    @Test
    public static void test1() throws FormatToNodeException, NodeToFormatException {

        var node = DEFAULT_JSON_NODE_CONVERTER.formatToNode(json, JsonNodeConvertConfig.of());

        var json1 = DEFAULT_JSON_NODE_CONVERTER.nodeToFormatString(node, JsonNodeConvertConfig.of());

        var node2 = DEFAULT_JSON_NODE_CONVERTER.formatToNode(json1, JsonNodeConvertConfig.of());

        Assert.assertEquals(node, node2);

    }

    @Test
    public static void test2() {
        var s = "[".repeat(100) + "]".repeat(100);
        Assert.expectThrows(FormatToNodeException.class, () -> {
            Node node = DEFAULT_JSON_NODE_CONVERTER.formatToNode(s, JsonNodeConvertConfig.of().maxNestingDepth(80));
        });

        var s1 = "{".repeat(100) + "}".repeat(100);
        Assert.expectThrows(FormatToNodeException.class, () -> {
            Node node = DEFAULT_JSON_NODE_CONVERTER.formatToNode(s1, JsonNodeConvertConfig.of().maxNestingDepth(80));
        });

    }

}
