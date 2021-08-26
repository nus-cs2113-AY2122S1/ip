public class Tasks {
    private int length = 0;
    String[] list = new String[100];
    boolean[] mark_as_done = new boolean[100];

    public void insert(String item)
    {
        list[this.length++] = item;
    }

    public void markDone(int index)
    {
        if(index > this.length) {
            System.out.println("\tOut of Index");
            return;
        }
        mark_as_done[index] = true;
        System.out.println("\t[X] " + list[index]);
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
            full_list += "\t" + (i+1) + "[" + (mark_as_done[i] ? "X" : " ") + "]" + list[i] + "\n";
        }
        return full_list;
    }


}
