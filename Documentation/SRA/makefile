dependencies:
	sudo apt-get install unzip && \
	wget http://www.umlet.com/umlet_14_2/umlet-standalone-14.2.zip && \
	unzip umlet-standalone-14.2.zip;

pdf: diagrams
	pdflatex main.tex;
	pdflatex main.tex;

diagrams:
	java -jar Umlet/umlet.jar -action=convert -format=pdf -filename=../Diagrams/deployment_diagram.uxf -output=../Diagrams/deployment_diagram.pdf;
	java -jar Umlet/umlet.jar -action=convert -format=pdf -filename=../Diagrams/uuc.uxf -output=../Diagrams/uuc.pdf;

