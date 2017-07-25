dependencies:
	sudo apt-get install unzip && \
	wget http://www.umlet.com/umlet_14_2/umlet-standalone-14.2.zip && \
	unzip umlet-standalone-14.2.zip;

pdf: diagrams
	pdflatex CSIR\ ETA.tex;
	pdflatex CSIR\ ETA.tex;

diagrams:
	java -jar Umlet/umlet.jar -action=convert -format=pdf -filename=Deployment\ Diagram.uxf -output=ETA-Deployment.pdf;
