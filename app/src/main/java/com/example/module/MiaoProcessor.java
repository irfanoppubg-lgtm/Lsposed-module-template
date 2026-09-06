package com.example.module;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MiaoProcessor {

    private static final String TAG = "MiaoProcessor";
    private static final Random RANDOM = new Random();

    private static final String[][] REPLACE_RULES = {
            {"您的", "主人的"}, {"你", "主人"}, {"您", "主人"},
            {"我", "本喵"}, {"我的", "本喵的"}, {"我们", "本喵们"}, {"我们的", "本喵们的"},
            {"吗", "呀"}, {"嘛", "呀"}, {"呢", "呢~"}, {"啊", "呀"},
            {"哦", "噢~"}, {"啦", "啦~"}, {"吧", "吧~"}, {"哟", "哟~"},
            {"好的", "好哒"}, {"好", "好呀"}, {"嗯", "嗯呢"},
            {"是的", "是呀"}, {"对", "对呀"}, {"好吧", "好叭"},
            {"行", "行呀"}, {"算了", "算啦"}, {"没事", "没事呀"},
            {"没关系", "没关系呀"}, {"谢谢", "谢谢呀"}, {"不客气", "不客气呀"},
            {"非常", "超"}, {"超级", "超"}, {"特别", "好"}, {"很", "好"},
            {"吃饭", "吃饭呀"}, {"睡觉", "睡觉呢"}, {"开心", "开心呢"},
            {"喜欢", "喜欢呢"}, {"知道", "知道呢"}, {"明白", "明白呢"},
            {"哈哈", "哈哈~"}, {"嘿嘿", "嘿嘿~"}, {"嘻嘻", "嘻嘻~"},
            {"嗯嗯", "嗯呢~"}, {"哦哦", "噢噢~"},
            {"!", "！"}, {"?", "？"}, {"...", "……"}, {"..", "……"}
    };

    private static final List<String> KAOMOJI_LIST = new ArrayList<>(Arrays.asList(
            "(´▽｀)", "(◕‿◕)", "(｡◕‿◕｡)", "(◡‿◡)", "(ᵔ◡ᵔ)",
            "(｡◡‿◡｡)", "(*´∀｀*)", "(˘︶˘)", "(˘▽˘)", "(・ω・)",
            "(´ω｀)", "(｡•́︿•̀｡)", "(=^･ω･^=)", "ฅ^•ﻌ•^ฅ", "(｡･ω･｡)",
            "(=ΦωΦ=)", "(=￣ω￣=)", "♪(´▽｀)", "☆(ﾉ◕ヮ◕)ﾉ", "ヽ(´▽｀)/",
            "✧(≖ ◡ ≖)", "(•̀ᴗ•́)و", "(＾▽＾)", "(⌒‿⌒)", "(＾ｖ＾)"
    ));

    private static final float EASTER_EGG_RATE = 0.10f;
    private static final String[] EASTER_EGGS = {
            "（瘫在沙发上不想动）", "（翻了个身继续玩手机）",
            "（往嘴里丢了颗糖）", "（裹着毯子缩成一团）",
            "（端着茶杯发呆）", "（趴在桌上闭目养神）",
            "（把脚翘在茶几上）", "（窝在懒人沙发里刷视频）",
            "（打了个哈欠，眼角泛泪花）", "（眯着眼睛晒太阳）",
            "（假装没听见你说话）", "（白了你一眼但嘴角在笑）",
            "（哼了一声转过头去）", "（嘴上说不要，身体很诚实）",
            "（装作很忙的样子）", "（用余光偷偷看你）",
            "（冷漠地点了点头）", "（抱臂靠在墙上，一脸不屑）",
            "（翘起二郎腿，晃着脚）", "（慢悠悠地瞟了你一眼）",
            "（悄悄把你的拖鞋藏起来）", "（在你背后比了个鬼脸）",
            "（偷偷翻了个白眼）", "（趁你不注意蹭了你一下）",
            "（往你杯子里丢了颗话梅）", "（在你的本子上画了只猫）",
            "（偷偷拍了下你的后脑勺）", "（把你的耳机拔掉一只）",
            "（在你旁边吹口哨）", "（把你的手机亮度调到最暗）",
            "（拉开一罐汽水，惬意地喝了一口）", "（躺在沙发上刷短视频）",
            "（翘着脚吃薯片）", "（抱着一包零食不撒手）",
            "（用吸管戳着杯子里的冰块）", "（打开冰箱看了半天，啥也没拿）",
            "（靠在窗边看外面的云）", "（磨磨蹭蹭不想起床）",
            "（抱着抱枕眯着眼）", "（盘腿坐着，撑着下巴发呆）",
            "（嘴上说“随便”，心里已经有答案）", "（说“烦死了”但嘴角有笑意）",
            "（嘟囔了一句，但没真的生气）", "（嘴上说“不用”，其实想要）",
            "（说“别理我”，但你在旁边就安心）", "（表面嫌弃，但还是接了话）",
            "（看着你，欲言又止）", "（走到哪，把手机带到哪）",
            "（去阳台晒了会儿太阳）", "（给花浇了水，顺便发了会儿呆）",
            "（翻了一页书，但没在看）", "（把拖鞋甩掉，光脚踩地板）",
            "（蹲在椅子上打字）", "（趴在床上看窗外）",
            "（用毯子把自己裹成球）", "（瘫在沙发上假装是一滩）",
            "（假装路过，实际过来瞄一眼）", "（悄悄竖起了耳朵）",
            "（在房间门口探头看了一眼）", "（假装只是路过，其实在偷看）",
            "（装作漫不经心，实则关注）", "（躲在门后吓你一跳，然后笑）",
            "（装作没发现你，偷偷拍你）"
    };

    public static String process(String original) {
        if (original == null || original.isEmpty()) return original;
        if (isAlreadyProcessed(original)) return original;
        String result = original;
        result = applyReplaceRules(result);
        result = compressPunctuation(result);
        result = applyEasterEgg(result);
        result = appendRandomKaomoji(result);
        Log.d(TAG, "✨ 处理前: " + original);
        Log.d(TAG, "🌸 处理后: " + result);
        return result;
    }

    private static boolean isAlreadyProcessed(String text) {
        if (text.contains("本喵") || text.contains("主人")) {
            for (String kao : KAOMOJI_LIST) {
                if (text.contains(kao)) return true;
            }
        }
        return false;
    }

    private static String applyReplaceRules(String text) {
        String result = text;
        List<String[]> sortedRules = new ArrayList<>(Arrays.asList(REPLACE_RULES));
        sortedRules.sort((a, b) -> Integer.compare(b[0].length(), a[0].length()));
        for (String[] rule : sortedRules) {
            result = result.replace(rule[0], rule[1]);
        }
        return result;
    }

    private static String compressPunctuation(String text) {
        text = text.replaceAll("！{2,}", "！");
        text = text.replaceAll("!{2,}", "！");
        text = text.replaceAll("？{2,}", "？");
        text = text.replaceAll("\\?{2,}", "？");
        text = text.replaceAll("。{2,}", "。");
        text = text.replaceAll("\\.{2,}", "……");
        text = text.replaceAll("~{2,}", "~");
        return text;
    }

    private static String applyEasterEgg(String text) {
        if (RANDOM.nextFloat() > EASTER_EGG_RATE) return text;
        String egg = EASTER_EGGS[RANDOM.nextInt(EASTER_EGGS.length)];
        if (RANDOM.nextBoolean()) return egg + " " + text;
        else return text + " " + egg;
    }

    private static String appendRandomKaomoji(String text) {
        for (String kao : KAOMOJI_LIST) {
            if (text.contains(kao)) return text;
        }
        String kaomoji = KAOMOJI_LIST.get(RANDOM.nextInt(KAOMOJI_LIST.size()));
        char last = text.charAt(text.length() - 1);
        if (last == '。' || last == '！' || last == '？' ||
            last == '.' || last == '!' || last == '?' ||
            last == '~' || last == '…') {
            return text + " " + kaomoji;
        }
        return text + " " + kaomoji;
    }
}
