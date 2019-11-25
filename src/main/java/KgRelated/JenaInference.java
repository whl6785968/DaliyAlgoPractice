package KgRelated;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.jena.graph.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.InfGraph;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class JenaInference {
    public static void getComedian(){
        String prefix = "http://www.test.com/kg/#";
        Graph data = Factory.createGraphMem();

        // 定义节点
        Node movie = NodeFactory.createURI(prefix + "movie");
        Node hasActedIn = NodeFactory.createURI(prefix + "hasActedIn");
        Node hasGenre = NodeFactory.createURI(prefix + "hasGenre");
        Node genreName = NodeFactory.createURI(prefix + "genreName");
        Node genre = NodeFactory.createURI(prefix + "genre");
        Node person = NodeFactory.createURI(prefix + "person");
        Node Comedian = NodeFactory.createURI(prefix + "Comedian");

        // 添加三元组
        data.add(new Triple(genre, genreName, NodeFactory.createLiteral("喜剧")));
        data.add(new Triple(movie, hasGenre, genre));
        data.add(new Triple(person, hasActedIn, movie));

        GenericRuleReasoner reasoner = (GenericRuleReasoner) GenericRuleReasonerFactory.theInstance().create(null);

        PrintUtil.registerPrefix("",prefix);

        reasoner.setRules(Rule.parseRules("[ruleComedian: (?p :hasActedIn ?m) (?m :hasGenre ?g) (?g :genreName '喜剧') -> (?p rdf:type :Comedian)] \n"
                + "-> tableAll()."));

        reasoner.setMode(GenericRuleReasoner.HYBRID);

        InfGraph infGraph = reasoner.bind(data);

        infGraph.setDerivationLogging(true);

        ExtendedIterator<Triple> tripleExtendedIterator = infGraph.find(person, null, null);
        while (tripleExtendedIterator.hasNext()){
            System.out.println(PrintUtil.print(tripleExtendedIterator.next()));
        }

    }

    public static void relatedTransaction(){
        Model myMod = ModelFactory.createDefaultModel();
        String finance = "http://www.example.org/kse/finance#";
        Resource 孙宏斌 = myMod.createResource(finance + "孙宏斌");
        Resource 融创中国 = myMod.createResource(finance + "融创中国");
        Resource 乐视网 = myMod.createResource(finance + "乐视网");
        Property 执掌 = myMod.createProperty(finance + "执掌");
        Resource 贾跃亭 = myMod.createResource(finance + "贾跃亭");
        Resource 地产公司 = myMod.createResource(finance + "地产公司");
        Resource 公司 = myMod.createResource(finance + "公司");
        Resource 法人实体 = myMod.createResource(finance + "法人实体");
        Resource 人 = myMod.createResource(finance + "人");
        Property 主要收入 = myMod.createProperty(finance + "主要收入");
        Resource 地产事业 = myMod.createResource(finance + "地产事业");
        Resource 王健林 = myMod.createResource(finance + "王健林");
        Resource 万达集团 = myMod.createResource(finance + "万达集团");
        Property 主要资产 = myMod.createProperty(finance + "主要资产");

        Property 股东 = myMod.createProperty(finance + "股东");
        Property 关联交易 = myMod.createProperty(finance + "关联交易");
        Property 收购 = myMod.createProperty(finance + "收购");

        myMod.add(孙宏斌, 执掌, 融创中国);
        myMod.add(贾跃亭, 执掌, 乐视网);
        myMod.add(王健林, 执掌, 万达集团);
        myMod.add(乐视网, RDF.type, 公司);
        myMod.add(万达集团, RDF.type, 公司);
        myMod.add(融创中国, RDF.type, 地产公司);
        myMod.add(地产公司, RDFS.subClassOf, 公司);
        myMod.add(公司, RDFS.subClassOf, 法人实体);
        myMod.add(孙宏斌, RDF.type, 人);
        myMod.add(贾跃亭, RDF.type, 人);
        myMod.add(王健林, RDF.type, 人);
        myMod.add(万达集团,主要资产,地产事业);
        myMod.add(融创中国,主要收入,地产事业);
        myMod.add(孙宏斌, 股东, 乐视网);
        myMod.add(孙宏斌, 收购, 万达集团);

        PrintUtil.registerPrefix("",finance);
        InfModel infModel = ModelFactory.createRDFSModel(myMod);
        subClassof(infModel,地产公司,法人实体);
//        // 输出当前模型
//        StmtIterator i = myMod.listStatements(null,null,(RDFNode)null);
//        while (i.hasNext()) {
//            System.out.println(" - " + PrintUtil.print(i.nextStatement()));
//        }
//
//        GenericRuleReasoner reasoner = (GenericRuleReasoner) GenericRuleReasonerFactory.theInstance().create(null);
//        reasoner.setRules(Rule.parseRules(
//                "[ruleHoldShare: (?p :执掌 ?c) -> (?p :股东 ?c)] \n"
//                        + "[ruleConnTrans: (?p :收购 ?c) -> (?p :股东 ?c)] \n"
//                        + "[ruleConnTrans: (?p :股东 ?c) (?p :股东 ?c2) -> (?c :关联交易 ?c2)] \n"
//                        + "-> tableAll()."));
//        reasoner.setMode(GenericRuleReasoner.HYBRID);
//
//        InfGraph bind = reasoner.bind(myMod.getGraph());
//        bind.setDerivationLogging(true);
//
//        System.out.println("推理后");
//
//        ExtendedIterator<Triple> tripleExtendedIterator = bind.find(null, null, null);
//
//        while(tripleExtendedIterator.hasNext()){
//            System.out.println(" - " + PrintUtil.print(tripleExtendedIterator.next()));
//        }
    }

    public static void subClassof(Model model,Resource resource,Resource property){
        for(StmtIterator i = model.listStatements(resource,RDFS.subClassOf,property);i.hasNext();){
            Statement stmt = i.nextStatement();
            System.out.println(stmt);
            System.out.println("yes!");
            break;
        }
    }

    public static void main(String[] args) {
//        getComedian();
        relatedTransaction();
    }
}
