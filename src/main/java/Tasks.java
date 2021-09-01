public class Tasks {
    private int length = 0;
    String[] list = new String[100];
    String[] category = new String[100];
    boolean[] mark_as_done = new boolean[100];

    public int getLength() {
        return length;
    }

    public void insert(String item, String type) {
        list[this.length] = item;
        category[this.length++] = type;
    }

    public void markDone(int index)
    {
        if(index >= this.length || index < 0) {
            System.out.println("\tBro, you suck at counting numbers");
            return;
        }
        System.out.println("\tNice! I have marked this done !");
        mark_as_done[index] = true;
        System.out.println(getItem(index));
    }

    public String getItem(int i) {
        return  "\t" + "[" + category[i] + "]" + "[" + (mark_as_done[i] ? "X" : " ") + "]" + list[i];
    }

    @Override
    public String toString() {
        String full_list = "\tHere are the tasks in your list:\n";
        if(this.length == 0)
        {
            return "\tWoohooo no tasks due ~~~~\n";
        }
        for(int i=0; i<this.length; i++)
        {
            full_list += "\t" + (i+1) +  ".[" + category[i] + "]" + "[" + (mark_as_done[i] ? "X" : " ") + "]" + list[i] + "\n";
        }
        return full_list;
    }
}
