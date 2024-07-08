#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <fstream>
#include <cstdlib>

using namespace std;

struct Station 
{
	string name;
	float x;
    	float y;
};

struct WtEdge 
{
    	int src, dest, weight;
};

vector<Station> stations;
vector<WtEdge> distances;

int findParent(vector<int>& parent, int i) 
{
	if (parent[i] == i)
        	return i;
    	return findParent(parent, parent[i]);
}

void unionSets(vector<int>& parent, int x, int y) 
{
  	int x_set = findParent(parent, x);
    	int y_set = findParent(parent, y);
    	parent[x_set] = y_set;
}

bool compareEdges(const WtEdge& a, const WtEdge& b) 
{
	return a.weight < b.weight;
}

void kruskalMST(vector<WtEdge>& mstEdges) 
{
	vector<int> parent(stations.size());
	for (int i = 0; i < stations.size(); ++i)
        	parent[i] = i;
	sort(distances.begin(), distances.end(), compareEdges);
	int i = 0, edgeCount = 0;
    	while (edgeCount < stations.size() - 1 && i < distances.size()) 
	{
        	WtEdge nextEdge = distances[i++];
	        int x = findParent(parent, nextEdge.src - 1);
	        int y = findParent(parent, nextEdge.dest - 1);
	        if (x != y) 
		{
            		mstEdges.push_back(nextEdge);
            		unionSets(parent, x, y);
            		edgeCount++;
        	}
    	}
}

void generateGraphviz(const vector<WtEdge>& mstEdges, const string& filename) 
{
	ofstream file(filename);
    	file << "graph MST {" << endl;
    	for (const auto& edge : mstEdges) 
	{
        	file << "    " << stations[edge.src - 1].name << " -- " << stations[edge.dest - 1].name << " [label=\"" << edge.weight << "\"];" << endl;
    	}
    	file << "}" << endl;
	file.close();
}

int calculateDistance(const Station& a, const Station& b) 
{
    	return round(sqrt(pow(a.x - b.x, 2) + pow(a.y - b.y, 2)));
}

/*
void printDistances() //Displaying edges
{
    	cout << "\nEDGES AND THEIR WEIGHTS:(EDGES)\n\n";
    	for (int i = 0; i < distances.size(); ++i) 
        	cout << "(" << distances[i].src <<" , " << distances[i].dest << ") -> " << distances[i].weight << endl;
}
*/

void printStations() //Displaying vertexes
{
    	cout << "\nSTATIONS AND THEIR CO-ORDINATES:(VERTEXES)\n\n";
    	for (int i = 0; i < stations.size(); ++i) 
    		cout << i + 1 << ".\t"<<stations[i].name<<" :\t\t(" << stations[i].x/10 << ", " << stations[i].y/10 << ")\n";
}

void calAllDistances() 
{
	for (int i = 0; i < stations.size(); ++i) 
    	{
    		for (int j = i + 1; j < stations.size(); ++j) 
		{
        		int distance = calculateDistance(stations[i], stations[j]);
            		WtEdge edge;
            		edge.src = i + 1;
            		edge.dest = j + 1;
            		edge.weight = distance;
            		distances.push_back(edge);
        	}	
    	}
}

int main() 
{
	int numberOfStations;

    	cout << "Enter the number of Railway stations: ";
    	cin >> numberOfStations;
    	cout << endl;

    	string name;
	float a,b;
    	
	for (int i = 0; i < numberOfStations; ++i) 
	{
        	Station station;
		cout << "Enter district " << i+1 <<" name :";
        	cin >> name;
		station.name=name;
	        cout << "Enter coordinates for station " << i + 1 << " (lattitude,longitude): ";
        	cin >> a >> b;
		station.x=a*10;
		station.y=b*10;
        	stations.push_back(station);
    	}
    	
	cout << "RESULT:" << endl;
    	printStations();
    	cout << endl << "CALCULATING ALL EDGES IN TERMS OF GREAT CIRCLE DISTANCE..." << endl;
    	calAllDistances();
    	//printDistances();
        cout << endl << "GRAPH ARE BEING GENERATED..." << endl;
    
    	try
    	{
    	
		vector<WtEdge> mstEdges;
    		kruskalMST(mstEdges);
		cout << endl << "COMPLETE GRAPH IS BEING GENERATED..." << endl;
    		cout << endl << "OPENING COMPLETE GRAPH!!\n" << endl;
    
		// Generate the Graphviz DOT file for initail complete graph
   		string filename1 = "cmp.dot";
    		generateGraphviz(distances, filename1);
	
    		cout << endl << "MINIMUM SPANNING TREE IS BEING GENERATED..." << endl;
    		cout << endl << "OPENING MST!!\n" << endl;
    		
		// Display the graph using Graphviz for initial complete graph
    		string command = "dot -Tpng " + filename1 + " -o cmp.png && xdg-open cmp.png";
    		system(command.c_str());
	
    		// Generate the Graphviz DOT file for MST
    		string filename2 = "mst.dot";
    		generateGraphviz(mstEdges, filename2);

     		// Display the graph using Graphviz for MST
    		command = "dot -Tpng " + filename2 + " -o mst.png && xdg-open mst.png";
    		system(command.c_str());
    	}
    
	catch(...)
    	{
		cout<<"Error in opening graph." << endl;
    	}
    	
	return 0;
}

