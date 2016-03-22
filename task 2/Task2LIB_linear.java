package Task2;

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.sourceData;
import weka.Fltrs.Fltr;
import weka.Fltrs.supervised.attribute.AttributeSelection;
public class Lib_linear
{
	public static void main(String[] args) throws Exception
	{
		sourceData source = new sourceData("C:\Users\Aravind\Desktop\Graduate Courses\CSE 6339_Dataset_1.csv");
		Instances Data = source.getDataSet();
		if (Data.classIndex() == -1)
			Data.setClassIndex(4);
		AttributeSelection Fltr = new AttributeSelection();
		CfsSubsetEval eval = new CfsSubsetEval();
		GreedyStepwise search = new GreedyStepwise();
		search.setSearchBackwards(true);
		Fltr.setEvaluator(eval);
		Fltr.setSearch(search);
		Fltr.setInputFormat(Data);
		Instances newData = Fltr.useFltr(Data, Fltr);
		Logistic svm = new Logistic();
		svm.buildClassifier(newData);
		Evaluation evaluation = new Evaluation(newData);
		evaluation.crossValidateModel(svm, newData, 10, new Random(1)); 
		System.out.println(evaluation.toSummaryString());
		double a[][]=evaluation.confusionMatrix();
		System.out.println("The confusion matrix is as follows: \n ");
		System.out.print("The true positives are as follows: " + a[0][0]+"\t");
		System.out.println("The false positives are as follows: " + a[0][1]);
		System.out.print("The false negatives are as follows: " + a[1][0]+"\t");
		System.out.println("The true negatives are: " + a[1][1]);
		System.out.println("The error rate of the model is:"+evaluation.errorRate());
		System.out.println(evaluation.toClassDetailsString());
	}

}
