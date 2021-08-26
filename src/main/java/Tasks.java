public class Tasks {
    private int length = 0;
    String[] list = new String[100];
    boolean[] mark_as_done = new boolean[100];

    public void insert(String item)
    {
        list[length++] = item;
    }

    public void markDone(int index)
    {
        index -= 1;
        if(index > length) return;
        mark_as_done[index] = true;
    }

    @Override
    public String toString() {
        String full_list = "";
        if(length == 0)
        {
            return "\tWoohooo no tasks due ~~~~\n";
        }
        for(int i=0; i<length; i++)
        {
            full_list += "\t" + (i+1) + "[" + (mark_as_done[i] ? "X" : " ") + "]" + list[i] + "\n";
        }
        return full_list;
    }
}
