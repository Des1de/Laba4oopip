package Command;

import Models.OrderInformation;

public interface Command {
    void execute(OrderInformation orderInformation);
}
