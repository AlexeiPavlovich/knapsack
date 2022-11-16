package com.example;

import org.junit.Test;

public class KnapsackTest {

	@Test
	public void TestKnapsack() {
		Pack[] packs = { new Pack(5, 10), new Pack(4, 40), new Pack(6,30) , new Pack(3,50)};
		//Pack[] packs = { new Pack(4, 10), new Pack(2, 4), new Pack(3,7)};
		//int capacity = 5;
		int capacity = 10;

		int mem[][] = new int[packs.length + 1][capacity + 1];

		for (int i = 0; i <= packs.length; i++) {
			for (int j = 0; j <= capacity; j++) {
				mem[i][j] = -1;
			}
		}

		System.out.println(KS(packs.length-1, packs, capacity, mem));
		//int sumPrice = Arrays.stream(arr).filter(ind -> ind != -1).map(ind -> packs[ind].price).reduce(0, Integer::sum);
		//System.out.println(sumPrice);
		//Arrays.stream(arr).filter(ind -> ind != -1).forEach(ind->System.out.println(packs[ind]));
		System.out.println(KS2(capacity,packs));

	}
	
	
	int KS2(int capacity,Pack[] packs) {
		
		
		int[][] table=new int[packs.length+1][capacity+1];
		
		
		for(int i=1;i<table.length;i++) {
			for(int j=1;j<table[i].length;j++) {
				int indexPack=i-1;
				int included=(j-packs[indexPack].width >=0 && packs[indexPack].width<=j) ? packs[indexPack].price+table[i-1][j-packs[indexPack].width] : 0;
				int excluded=table[i-1][j];
				table[i][j]=Math.max(included, excluded);
				
			}
			
		}

		
		/*for(int i=0;i<table.length;i++) {
			for(int j=0;j<table[i].length;j++) {
				System.out.print(" "+table[i][j]+" ");
			}
			System.out.println();
		}*/
		return table[packs.length][capacity];
	}

	int KS(int indexPack, Pack[] packs, int capacity, int mem[][]) {
		if (indexPack == 0) {
			return 0;
		}
		int nextIndex = indexPack - 1;
		if (mem[indexPack][capacity] != -1) {
			return mem[indexPack][capacity];
		}
		if (packs[indexPack].width > capacity) {
			return mem[nextIndex][capacity] = KS(nextIndex, packs, capacity, mem);
		} else {

			int included = mem[indexPack][capacity] = packs[indexPack].price + KS(nextIndex, packs, capacity - packs[indexPack].width, mem);
			int excluded = mem[nextIndex][capacity] = KS(nextIndex, packs, capacity, mem);
	
			return Math.max(included, excluded);
		}
	}

	private static class Pack {

		@Override
		public String toString() {
			return "Pack [width=" + width + ", price=" + price + "]";
		}
		public Pack(int width, int price) {
			this.width = width;
			this.price = price;
		}

		Integer width;
		Integer price;

	}
}
