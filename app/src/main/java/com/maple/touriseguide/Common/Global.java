package com.maple.touriseguide.Common;

import com.maple.touriseguide.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rrr on 2017/10/27.
 */

public class Global {
    public static List<Map<String, Object>> dynamic = new ArrayList<>();
    public static List<Map<String, Object>> sug = new ArrayList<>();
    public static String IP = "http://192.168.15.174:8001";
    //public static String MyIP = "http://60.177.49.211:8080";
    public static String MyIP = "http://47.100.97.74:8080/guideserver";

    public static void initdata(){
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("head_pic", R.drawable.tx);
        map1.put("share_name", "萧枫");
        map1.put("share_time", "2017-12-20");
        map1.put("share_content", "第一条！沙发！");
        map1.put("share_pic", R.drawable.fir);
        dynamic.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("head_pic", R.drawable.tx);
        map2.put("share_name", "萧枫");
        map2.put("share_time", "2017-12-21");
        map2.put("share_content", "第一条！沙发！");
        map2.put("share_pic", R.drawable.sec);
        dynamic.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("head_pic", R.drawable.tx);
        map3.put("share_name", "萧枫");
        map3.put("share_time", "2017-12-22");
        map3.put("share_content", "第一条！沙发！");
        map3.put("share_pic", R.drawable.tx);
        dynamic.add(map3);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("head_pic", R.drawable.tx);
        map4.put("share_name", "萧枫");
        map4.put("share_time", "2017-12-23");
        map4.put("share_content", "第一条！沙发！");
        map4.put("share_pic", R.drawable.thr);
        dynamic.add(map4);

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("head_pic", R.drawable.tx);
        map5.put("share_name", "萧枫");
        map5.put("share_time", "2017-12-24");
        map5.put("share_content", "第一条！沙发！");
        map5.put("share_pic", R.drawable.forth);
        dynamic.add(map5);

        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("head_pic", R.drawable.tx);
        map6.put("share_name", "萧枫");
        map6.put("share_time", "2017-12-25");
        map6.put("share_content", "第一条！沙发！");
        map6.put("share_pic", null);
        dynamic.add(map6);

        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("head_pic", R.drawable.tx);
        map7.put("share_name", "萧枫");
        map7.put("share_time", "2017-12-26");
        map7.put("share_content", "第一条！沙发！");
        map7.put("share_pic", null);
        dynamic.add(map7);

        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("head_pic", R.drawable.tx);
        map8.put("share_name", "萧枫");
        map8.put("share_time", "2017-12-27");
        map8.put("share_content", "第一条！沙发！");
        map8.put("share_pic", R.drawable.fif);
        dynamic.add(map8);

        Map<String, Object> map9 = new HashMap<String, Object>();
        map9.put("head_pic", R.drawable.tx);
        map9.put("share_name", "萧枫");
        map9.put("share_time", "2017-12-28");
        map9.put("share_content", "第一条！沙发！");
        map9.put("share_pic", null);
        dynamic.add(map9);
    };

    public static void adddata(String user_name, String share_time,String share_content){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("head_pic", R.drawable.tx);
        map.put("share_name", user_name);
        map.put("share_time", share_time);
        map.put("share_content", share_content);
        map.put("share_pic", null);
        dynamic.add(0,map);
    }

    public static void initsug(int sug_id){
        Map<String, Object> map0 = new HashMap<String, Object>();
        map0.put("sug_id", 0);
        map0.put("sug_pic", R.drawable.pic1);
        map0.put("sug_title", "西湖");
        map0.put("sug_content", "西湖无疑是杭州之美的代表，著名的“西湖十景”环绕湖边，自然与人文相互映衬，组成了杭州旅行的核心地带。你不必执着于走遍每个景点，倒可以花上半天或一天在湖边徜徉一番，无论怎么玩，都让人心情舒畅。西湖概览游玩西湖可以步行、坐游船、乘电瓶车，也可以自驾或者骑行。");
        sug.add(map0);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("sug_id", 1);
        map1.put("sug_pic", R.drawable.pic2);
        map1.put("sug_title", "灵隐寺");
        map1.put("sug_content", "灵隐寺又称“云林寺”，坐落于西湖西面的灵隐山麓。寺内环境清幽，是杭州最早的名刹，留存着各朝代众多精美文物。这里是济公的出家地，据说求愿很灵验，每天来礼佛祈福的香客络绎不绝。一千七百年的风雨，使灵隐寺成为历史与文化的宝库：天王殿上悬“云林禅寺”匾额，为清康熙帝所题；大雄宝殿前的石塔、天王殿前的石经幢均是五代十国吴越时的遗物；寺内珍藏的佛教文物有古代贝叶写经、东魏镏金佛像、明董其昌写本《金刚经》、清雍正木刻龙藏等珍贵宝物。");
        sug.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("sug_id", 2);
        map2.put("sug_pic", R.drawable.pic3);
        map2.put("sug_title", "宋城");
        map2.put("sug_content", "宋城位于杭州西南市郊，是一座大型宋代文化主题乐园。充满宋代风情的仿古茶楼、杂货铺、打铁铺、酒坊等随处可见。街上有木偶戏、皮影戏、打擂台等表演，还会遇到披甲士兵巡街或是缉拿逃犯，给人一种奇妙的穿越感。来了宋城就一定要看《宋城千古情》，通过大型的歌舞秀，你会看到良渚古人的生活、宋朝皇宫的辉煌、岳家军的惨烈以及《梁祝》、《白蛇传》的千古绝唱，体验一回心灵的震撼。");
        sug.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("sug_id", 3);
        map3.put("sug_pic", R.drawable.pic4);
        map3.put("sug_title", "杭州动物园");
        map3.put("sug_content", "杭州动物园位于虎跑路40号，原址在钱王祠。临近虎跑梦泉、六和塔，北接少儿公园（满陇桂雨），与玉皇飞云遥遥相对，是一个山林式动物园。园内设有大象馆、大熊猫馆、长颈鹿馆、两栖爬虫馆、杂技馆等20余个场馆，其中熊猫馆内两只大熊猫，名叫成就、双好；杂技馆内每天都会有10多场驯象、驯虎、驯狮等精彩表演，每场表演时间约30-40分钟。");
        sug.add(map3);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("sug_id", 4);
        map4.put("sug_pic", R.drawable.pic5);
        map4.put("sug_title", "清河坊街");
        map4.put("sug_content", "清河坊街是杭州人气最旺的商业区之一，沿街的商铺是老建筑改建的，古色古香。这里各种本地小吃、老字号、茶楼云集，加上背靠吴山，又与美食街高银街近邻，无论是游客还是本地人都常来光顾。小吃和购物这个商业步行街区不算太大，商铺种类很全，算得上杭城特色的缩影，当地特产在这里基本都能找到。遇到卖定胜糕、酥油饼、龙须糖等当地小食的，不妨买来尝尝。");
        sug.add(map4);

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("sug_id", 5);
        map5.put("sug_pic", R.drawable.pic6);
        map5.put("sug_title", "雷峰塔");
        map5.put("sug_content", "雷峰塔又名“黄妃塔”，位于西湖南岸夕照山的雷峰上。很多人的“雷峰塔情结”源于白娘子传说，尽管旧塔在1924年就倒掉了，如今这座是在原塔基上新修的，里面有电梯和空调，但并不妨碍你来此寻访白娘子的踪迹。景区看点雷峰塔本身是整个景区最大的看点，新修的雷峰塔更像一个博物馆，从底座往上共有5层。");
        sug.add(map5);

        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("sug_id", 6);
        map6.put("sug_pic", R.drawable.pic7);
        map6.put("sug_title", "长乔极地海洋公园");
        map6.put("sug_content", "长乔极地海洋公园位于湘湖之畔，在这里不仅可以看到海狮、海豚等各种可爱的海洋生物，还有精彩的的动物表演。它虽然不在杭州市中心，但有地铁通到附近，交通方便，适合周末带孩子一起去玩。海洋公园是一个三层的室内场馆，包括极地、雨林、深海三个主题展览，顺着参观路线依次游览，那些来自遥远海洋的动物们近在咫尺。");
        sug.add(map6);

        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("sug_id", 7);
        map7.put("sug_pic", R.drawable.pic8);
        map7.put("sug_title", "三潭印月");
        map7.put("sug_content", "三潭印月又称“小瀛洲”，位于西湖中部偏南的湖面上，是西湖三岛中面积最大、唯一能上岛游览的一个。岛屿的轮廓像一个“田”字，外圈的环形上是优美的江南园林，内部被十字形的岛和桥分割成四个小湖，充满诗情画意。乘船登岛上岛必须乘坐西湖游船，沿着西湖有多个码头，其中花港、中山、杭饭（岳庙）码头的游船停在岛的西面，而湖滨（市区）上岛的游船则停在东面。");
        sug.add(map7);

        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("sug_id", 8);
        map8.put("sug_pic", R.drawable.pic9);
        map8.put("sug_title", "杭州野生动物世界");
        map8.put("sug_content", "杭州野生动物世界位于杭州近郊，和普通动物园比起来，这里的动物们要自在得多，游人也可以更近距离地观看它们。此外还有不少有趣的免费表演和游乐项目，很适合带小朋友来玩。游园概览景区分为步行区和行车区两部分，可以从正门购票步行入园，也可从右边的自驾游入口付车辆通行费自驾入园（220元/辆）。");
        sug.add(map8);

        Map<String, Object> map9 = new HashMap<String, Object>();
        map9.put("sug_id", 9);
        map9.put("sug_pic", R.drawable.pic10);
        map9.put("sug_title", "西溪国家湿地公园");
        map9.put("sug_content", "西溪国家湿地公园位于市区西面，这里环境清幽、水道纵横，是城市中少有的天然湿地，有“杭州之肾”之称。坐船漫游芦苇荡、寻迹《非诚勿扰》取景处，或是探访隐于林中的秋雪庵、梅竹山庄等古迹，都是非常享受的体验。西溪概览西溪湿地分为东区和西区，东区包括一期和二期，西区为三期洪园景区。东区是西溪湿地自然风光的精华所在，坐船穿行于错综复杂的水道，是感受自然生态最佳的方式。");
        sug.add(map9);

        Map<String, Object> map10 = new HashMap<String, Object>();
        map10.put("sug_id", 10);
        map10.put("sug_pic", R.drawable.pic11);
        map10.put("sug_title", "断桥");
        map10.put("sug_content", "断桥位于白堤北端，连着北山路。《白蛇传》中许仙、白娘子断桥相会的桥段使断桥尽人皆知，也给这座长桥添了许多浪漫色彩。游人来到杭州，总免不了要来这里走走，甚至憧憬一下在桥上碰到自己百年修来的“有缘人”。断桥景致最好的时候是冬日雪后，此时桥的阳面冰雪消融，但阴面仍被残雪覆盖，从高处看，桥似断非断，这就是著名的“断桥残雪”，也是最早的“西湖十景”之一。");
        sug.add(map10);

        Map<String, Object> map11 = new HashMap<String, Object>();
        map11.put("sug_id", 11);
        map11.put("sug_pic", R.drawable.pic12);
        map11.put("sug_title", "西湖音乐喷泉");
        map11.put("sug_content", "西湖音乐喷泉位于湖滨三公园附近的湖面上，是夜游西湖不可错过的景观。这里紧邻繁华的商圈，湖滨银泰、浣纱路、平海路等都不算远，吃喝玩乐一应俱全。晚上在这边逛街吃饭，顺便欣赏音乐喷泉，看西湖夜景，非常惬意。入夜后的喷泉因为有彩色灯光效果，看起来比白天更壮观。无数水柱在夜色中配合着灯光、音乐的节奏舞动，美轮美奂。");
        sug.add(map11);

        Map<String, Object> map12 = new HashMap<String, Object>();
        map12.put("sug_id", 12);
        map12.put("sug_pic", R.drawable.pic13);
        map12.put("sug_title", "灵隐（飞来峰）景区");
        map12.put("sug_content", "灵隐（飞来峰）景区位于西湖西面，包括灵隐寺和飞来峰两个主要景观。飞来峰不大也不高，却汇聚了几百处不同朝代的菩萨、高僧造像，有种融合之美。山中的灵隐寺是杭州最古老的寺院，香火旺盛，可以买香花券入寺祈愿。景区本身并不大，步行游玩即可。进入大门后往左，没一会儿就能看到飞来峰，沿着林中小径不出5分钟看到青林洞入口，据说洞前的石板曾是济公的睡床，睡一睡将不再为尘世所扰，引得许多游客在这里躺一躺。");
        sug.add(map12);

        Map<String, Object> map13 = new HashMap<String, Object>();
        map13.put("sug_id", 13);
        map13.put("sug_pic", R.drawable.pic14);
        map13.put("sug_title", "九溪烟树");
        map13.put("sug_content", "九溪烟树俗称“九溪十八涧”，位于西湖西边群山中的鸡冠垅下。九溪是由众多小溪流汇合而成的“Y”字形溪涧，“九溪烟树”即是每逢雨后，附近八觉山上的树林烟雾升腾，满谷迷蒙，形成好看的“烟树”景观。整个九溪长约5.5公里，沿着溪流是葱茏的树木和大片的茶园，顺着沿溪的林荫路悠闲地走走，有种人在画中游的感觉，非常享受。尤其天气暖的时候，还会看见不少在溪边玩水的大人和小孩。");
        sug.add(map13);

        Map<String, Object> map14 = new HashMap<String, Object>();
        map14.put("sug_id", 14);
        map14.put("sug_pic", R.drawable.pic15);
        map14.put("sug_title", "南宋御街");
        map14.put("sug_content", "南宋御街与河坊街相交，原来是南宋临安城的中轴线，后来由著名建筑师王澍设计改建，修缮了原本的老房子，加入了流水景观，使它成了今天热闹而古朴的商业街。鼓楼至西湖大道一段，是南宋御街的主要游览区域。虽然不少连锁店在这条老街上入驻，这里仍保留了不少本地特色的老店铺，比如万隆火腿庄、羊汤饭店等。");
        sug.add(map14);

        Map<String, Object> map15 = new HashMap<String, Object>();
        map15.put("sug_id", 15);
        map15.put("sug_pic", R.drawable.pic16);
        map15.put("sug_title", "杭州乐园");
        map15.put("sug_content", "杭州乐园是宋城集团旗下的大型主题公园之一，位于萧山区，杭州烂苹果乐园对面。和烂苹果乐园适合亲子的全室内游乐场不同，杭州乐园有不少大型户外游乐设施，悬挂过山车、大摆锤等等，走的是惊险刺激路线。园区分为童话王国、玛雅部落、失落丛林、吴越古城、冒险岛等5部分，其中绝大部分游乐项目都是包含在门票里的，只有摩天轮等少数几个项目需要另外付费，根据喜好决定是否参加即可。");
        sug.add(map15);

        Map<String, Object> map16 = new HashMap<String, Object>();
        map16.put("sug_id", 16);
        map16.put("sug_pic", R.drawable.pic17);
        map16.put("sug_title", "太子湾公园");
        map16.put("sug_content", "太子湾公园位于西湖的西南角，花港观鱼景区南门正对面，离苏堤、杨公堤都不远。这里春天的郁金香最为出名，此外，无论你什么时候来到这里，都能看到不少拍婚纱照的新人和约会的情侣，让整个公园充满了浪漫气息。从苏堤对面的东门进去，不远就是一块大草坪，每天早上都有不少人来此晨练。草坪上有一座充满异域风情的大风车，是太子湾公园的标志。");
        sug.add(map16);

        Map<String, Object> map17 = new HashMap<String, Object>();
        map17.put("sug_id", 17);
        map17.put("sug_pic", R.drawable.pic19);
        map17.put("sug_title", "千岛湖中心湖区");
        map17.put("sug_content", "千岛湖中心湖区包含梅峰岛（梅峰揽胜）、渔乐岛、月光岛、龙山岛4个岛屿。如果时间有限，中心湖区是游览千岛湖的首选，乘船则是大部分游人都会选择的游览方式。在梅峰揽胜观景台俯瞰千岛湖全景，是来到千岛湖的必做之事。上山和下山可以选择坐缆车、滑草或步行，走起来不会太耗体力。渔乐岛上有喂鱼活动，也是湖上仅有的可以用餐的岛屿，不过性价比一般。");
        sug.add(map17);

        Map<String, Object> map18 = new HashMap<String, Object>();
        map18.put("sug_id", 18);
        map18.put("sug_pic", R.drawable.pic18);
        map18.put("sug_title", "苏堤");
        map18.put("sug_content", "苏堤贯穿西湖南北，是苏东坡在杭州做官时所建，也叫“苏公堤”。因春景迷人，亦被称为“苏堤春晓”。苏堤全长近3公里，堤上柏油路两侧柳树成荫。你可以漫步欣赏三潭印月、雷峰塔等西湖美景，人少时骑单车也很不错。沿途景点苏堤由南向北有映波、锁澜、望山、压堤、东浦、跨虹六座石桥。");
        sug.add(map18);

        Map<String, Object> map19 = new HashMap<String, Object>();
        map19.put("sug_id", 19);
        map19.put("sug_pic", R.drawable.pic20);
        map19.put("sug_title", "大明山");
        map19.put("sug_content", "大明山位于临安西部，邻近安徽省。这里属黄山余脉，既承袭了黄山的奇峰怪石，又拥有浙西山水的明秀。春夏可踏青避暑，冬季的大明山滑雪场是滑雪胜地。穿越万米岩洞、走悬空栈道，或坐旱滑道下山，都是不错的体验。如果体力不错，可在景区门口乘坐景区小巴至“龙门峡口”下，便可开始徒步登山。");
        sug.add(map19);

        Map<String, Object> temp = sug.get(sug_id);
        sug.remove(sug_id);
        Collections.shuffle(sug);
        sug.add(0,temp);
    }
}