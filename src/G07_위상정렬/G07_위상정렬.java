package G07_위상정렬;
// 방향성이 있는 비순환 그래프를 정렬하는 알고리즘
// 선후관계, 일의 순서 or 선수과목 등 표기

import java.util.*;
import java.io.*;

public class G07_위상정렬 {

	// 노드의 개수(V)와 간선의 개수(E)
	// 노드의 개수는 최대 100,000개라고 가정
	public static int v, e;
	// 모든 노드에 대한 진입차수는 0으로 초기화
	public static int[] indegree = new int[100001];
	// 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트 초기화
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G07_위상정렬/G07_위상정렬.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		// 그래프 초기화
		for (int i = 0; i <= v; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// 방향 그래프의 모든 간선 정보를 입력 받기
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b); // 정점 A에서 B로 이동 가능
			// 진입 차수를 1 증가
			indegree[b] += 1;
		}

		topologySort();
	}

	// 위상정렬 함수
	private static void topologySort() {
		ArrayList<Integer> result = new ArrayList<>(); // 알고리즘 수행 결과를 담을 리스트
		Queue<Integer> q = new LinkedList<>(); // 큐 라이브러리 사용

		// 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
		for (int i = 1; i <= v; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			// 큐에서 원소 꺼내기
			int now = q.poll();
			result.add(now);
			// 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
			for (int i = 0; i < graph.get(now).size(); i++) {
				indegree[graph.get(now).get(i)] -= 1;
				// 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
				if (indegree[graph.get(now).get(i)] == 0) {
					q.offer(graph.get(now).get(i));
				}
			}
		}

		// 위상 정렬을 수행한 결과 출력
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}

}
