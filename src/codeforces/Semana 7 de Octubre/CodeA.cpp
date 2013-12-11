#include <iostream>
#include <vector>

using namespace std;

int main()
{
	int n;cin>>n;
	vector<int> nums(n);
	vector<int> veces(6,0);
	for(int i = 0; i< n; ++i){
		cin>>nums[i];
		veces[nums[i]]++;
	}
	if(veces[0]==0)
			cout<<-1<<endl;
	else if(veces[5] < 9){
		cout<<0<<endl;
	}else{
		int t = veces[5]/9;
		t*=9;
		for(int i = 0; i < t; ++i)
			cout<<'5';
		for(int i = 0; i< veces[0]; ++i)
			cout<<'0';
		cout<<endl;
	}
	return 0;
}