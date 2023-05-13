package week12;

import java.util.*;

class PROG_베스트앨범 {

	static class Song implements Comparable<Song> {
		String genre;
		int plays;
		int idx;
		int genrePlays;

		public Song(String genre, int plays, int idx) {
			super();
			this.genre = genre;
			this.plays = plays;
			this.idx = idx;
		}

		@Override
		public int compareTo(Song o) {
			if (o.genrePlays != this.genrePlays)
				return o.genrePlays - this.genrePlays;
			if (o.plays != this.plays)
				return o.plays - this.plays;
			return this.idx - o.idx;
		}
	}

	static List<Song> songList = new ArrayList<>();
	static Map<String, Integer> genreTotal = new HashMap<>();

	public int[] solution(String[] genres, int[] plays) {
		int size = genres.length;

		List<Integer> answerList = new ArrayList<>();
		for (int i = 0; i < size; i++) {

			songList.add(new Song(genres[i], plays[i], i));
			// 장르별 총 play수 저장, getOrDefault로 초기값 0 넣어줌
			genreTotal.put(genres[i], genreTotal.getOrDefault(genres[i], 0) + plays[i]);

		}

		for (int i = 0; i < size; i++) {
			Song curSong = songList.get(i);
			curSong.genrePlays = genreTotal.get(curSong.genre);
		}

		Collections.sort(songList);

		// 장르별로 2개씩만 저장, 같은 장르값이 들어올때마다 cnt++ 해주고
		// cnt가 2보다 작을때만 저장함
		int sameCnt = 1;
		String curGen = null;
		for (int i = 0; i < size; i++) {
			Song song = songList.get(i);
			if (song.genre.equals(curGen)) {
				if (sameCnt < 2) {
					answerList.add(song.idx);
					sameCnt++;
				}
			} else {
				answerList.add(song.idx);
				sameCnt = 1;
				curGen = song.genre;
			}

		}
		int[] answer = new int[answerList.size()];
		for (int i = 0; i < answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}
		return answer;
	}
}
