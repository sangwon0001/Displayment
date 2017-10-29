class Position
	{
		public int row;
		public int col;
		Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public Position up(){
			return new Position(this.row-1,this.col);
		}
		public Position down(){
			return new Position(this.row+1, this.col);
		}
		public Position left(){
			return new Position(this.row, this.col-1);
		}
		public Position right(){
			return new Position(this.row, this.col+1);
		}
	}