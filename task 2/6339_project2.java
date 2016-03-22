package Task2;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.J48;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSrc;
import weka.filters.Filter;

public class 6339_project2
{
	public static void main(String[] args) throws Exception
	{
		DataSrc Src = new DataSrc("C:\Users\Aravind\Desktop\Graduate Courses\CSE 6339_Dataset_1.csv");
		Instances data = Src.getDataSet();
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 17);
		AttributeSelectedClassifier classifier = new AttributeSelectedClassifier();
		CfsSubsetEval eval = new CfsSubsetEval();
		GreedyStepwise search = new GreedyStepwise();
		search.setSearchBackwards(true);
		J48 base = new J48();
		classifier.setClassifier(base);
		classifier.setEvaluator(eval);
		classifier.setSearch(search);
		Evaluation evaluation = new Evaluation(data);
		evaluation.crossValidateModel(classifier, data, 10, new Random(1)); 
		System.out.println(evaluation.toSummaryString());
		double a[][] = evaluation.confusionMatrix();
		System.out.println("The true positives are: " + a[0][0]);
		System.out.println("The false positives are: " + a[0][1]);
		System.out.println("The false negatives are: " + a[1][0]);
		System.out.println("The true negative are: " + a[1][1]);
		AttributeSelection attsel = new AttributeSelection();
		attsel.setEvaluator(eval);
		attsel.toResultsString();
		attsel.setSearch(search);
		attsel.SelectAttributes(data);
		int[] indices = attsel.selectedAttributes();
		System.out.println(Utils.arrayToString(indices));
	}
}